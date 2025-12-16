import requests
import pytest


#pytest -v -s tests/test_api_testing_demo.py
@pytest.fixture(scope="module")
def base_url():
    return "https://jsonplaceholder.typicode.com/"

@pytest.fixture(scope="module")
def session():
    session = requests.Session()
    session.headers.update({
        "Accept": "application/json",
        "Content-Type": "application/json"
    })
    yield session
    session.close()

@pytest.fixture(scope="module")
def sample_post():
    return {
        "title": "Sample Post",
        "body": "Sample Body Content",
        "userId": 1
    }

class TestBasicRequest:
    def test_get_single_post(self, base_url, session):
        response = session.get(base_url + "/posts/1")
        assert response.status_code == 200

        data = response.json()
        assert data["id"] ==1
        assert "title" in data

    @pytest.mark.parametrize("value", [
        1,3,7,9,14,18
    ])
    def test_get_single_post_with_params(self, base_url, session,value):
        response = session.get(base_url + "/posts/" + str(value))
        data = response.json()

        assert data["id"] ==value
        assert response.status_code == 200

        print(data["title"])

    def test_create_post(self, base_url, session,sample_post):
        response = session.post(base_url + "/posts", json=sample_post)
        assert response.status_code == 201
        data = response.json()
        assert data["id"] == 101
        assert data["title"] == "Sample Post"
        assert data["body"] == "Sample Body Content"

        print(data)

    @pytest.mark.parametrize("postId", [
        1,3
    ])
    def test_get_post_by_id(self, base_url, session, postId):
        response = session.get(base_url + "/posts/" + str(postId))
        assert response.status_code == 200
        data = response.json()

        print(data)

    @pytest.mark.parametrize("user_id,expected_name", [
        (1, "Leanne Graham"),
        (2, "Ervin Howell"),
        (3, "Clementine Bauch"),
        (4, "Patricia Lebsack"),
        (5, "Chelsey Dietrich")
    ])
    def test_user_names(self, base_url, session, user_id, expected_name):
        """Test user names match expected values"""
        response = session.get(f"{base_url}/users/{user_id}")

        assert response.status_code == 200
        assert response.json()["name"] == expected_name



    @pytest.mark.parametrize("endpoint,expected_count", [
        ("/posts", 100),
        ("/users", 10),
        ("/comments", 500),
        ("/albums", 100),
        ("/photos", 5000),
        ("/todos", 200)
    ])
    def test_resource_counts(self, base_url, session, endpoint, expected_count):
        """Test that each endpoint returns expected number of items"""
        response = session.get(f"{base_url}{endpoint}")

        assert response.status_code == 200
        assert len(response.json()) == expected_count

    # ==========================================================
    # RESPONSE VALIDATION
    # ==========================================================

    class TestResponseValidation:
        """
        Comprehensive response validation patterns.
        """

        def test_response_time(self, base_url, session):
            """Test response time is acceptable"""
            response = session.get(f"{base_url}/posts/1")

            assert response.status_code == 200
            # Response time in seconds
            assert response.elapsed.total_seconds() < 2.0

        def test_response_headers(self, base_url, session):
            """Test response headers"""
            response = session.get(f"{base_url}/posts/1")

            assert "Content-Type" in response.headers
            assert "application/json" in response.headers["Content-Type"]

        def test_json_structure(self, base_url, session):
            """Test JSON response structure"""
            response = session.get(f"{base_url}/users/1")
            data = response.json()

            # Required fields exist
            required_fields = ["id", "name", "username", "email", "address", "phone", "website", "company"]
            for field in required_fields:
                assert field in data, f"Missing required field: {field}"

            # Nested structure
            assert "street" in data["address"]
            assert "city" in data["address"]
            assert "geo" in data["address"]
            assert "lat" in data["address"]["geo"]
            assert "lng" in data["address"]["geo"]

        def test_email_format(self, base_url, session):
            """Test email format validation"""
            response = session.get(f"{base_url}/users/1")
            email = response.json()["email"]

            assert "@" in email
            assert "." in email.split("@")[1]

        def test_all_posts_have_required_fields(self, base_url, session):
            """Test all posts have required fields"""
            response = session.get(f"{base_url}/posts")
            posts = response.json()

            for post in posts:
                assert "id" in post
                assert "userId" in post
                assert "title" in post
                assert "body" in post
                assert isinstance(post["id"], int)
                assert isinstance(post["userId"], int)





#pytest -v -s tests/test_api_testing_demo.py