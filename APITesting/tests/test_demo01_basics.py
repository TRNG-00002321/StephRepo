import requests


# pytest -v -s tests/test_demo01_basics.py
def test_basics():
    response = requests.get("http://jsonplaceholder.typicode.com/users/1")
    print(response.status_code)
    #print(response.json())
    print(response.text)

    assert response.status_code == 200

def test_params():
    #"http://jsonplaceholder.typicode.com/comments?postId=1"
    #response = requests.get("http://jsonplaceholder.typicode.com/comments")
    params = {"postId": 1}
    response = requests.get("http://jsonplaceholder.typicode.com/comments", params=params)

def test_post():
    user_data = {"name": "Steph", "email": "tacocatSupreme@bacotell.org","role":"user"}
    response = requests.post("http://jsonplaceholder.typicode.com/users", data=user_data)
    print(response.status_code)
    print(response.text)


def test_get():
    try:
        response = requests.get("https://jsonplaceholder.typicode.com/posts/999")
        print(response.status_code)
        print(response.text)

        assert response.status_code == 404
    except requests.exceptions.HTTPError as e:
        print(e)
        assert e.response.text == "404 Not Found"





# pytest -v -s tests/test_demo01_basics.py