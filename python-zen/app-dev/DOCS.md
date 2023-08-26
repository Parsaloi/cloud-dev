
> <https://flask.palletsprojects.com/en/2.3.x/quickstart/>

## Deploying to a Web Server...

## Using Flask Extensions

**Extensions** are packages that help you accomplish common tasks. For example, `Flask-SQLAlchemy` provides `SQLAlchemy` support that makes it *simple*  
and *easy* to use with Flask :)

More extensions : <https://flask.palletsprojects.com/en/2.3.x/extensions/>

## Hooking in WSGI Middleware

To add *middleware* to your Flask application, wrap the application's `wsgi_app` attribute. For example, to apply `Werkzeug's` **ProxyFix**  
middleware <https://werkzeug.palletsprojects.com/en/2.3.x/middleware/proxy_fix/#werkzeug.middleware.proxy_fix.ProxyFix> for running behind `Nginx`:  

```python
from werkzeug.middleware.proxy_fix import ProxyFix
app.wsgi_app = ProxyFix(app.wsgi_app)
```

Wrapping `app.wsgi_app` instead of `app` means that `app` still points at you Flask application, not at the middleware, so you can continue to use  
and configure `app` directly

## Logging

Sometimes (most of the times for me :|) you might be in a situation where you deal with data that should be correct, but actually is not.  
For example you may have some client-side code that sends an HTTP request to the server but it's obviously malformed. This might be caused by a user  
tampering with the data, or the client code failing. Most of the time it's okay to reply with `400 Bad Request` in that situation, but sometimes  
that won't do and code has to continue working...Indeed!

You may want to log that something fishy happened :) This is where loggers come in handy. As of `Flask 0.3` a logger is preconfigured for you to use.

Here are some examples log calls:  

```python
app.logger.debug('A value for debugging')
app.logger.warning('A warning occurred (%d apples)', 42)
app.logger.error('An error occurred')
```

The attached logger <https://flask.palletsprojects.com/en/2.3.x/api/#flask.Flask.logger> is a standard logging Logger  
<https://docs.python.org/3/library/logging.html#logging.Logger>, so head over to the official logging docs for more information  
<https://docs.python.org/3/library/logging.html#module-logging>

See Error Handling docs here -> <https://flask.palletsprojects.com/en/2.3.x/errorhandling/>

## Message Flashing

Good applications and user interfaces are all about feedback. If the user does not get enough feedback they will probably end up hating the application  
*Flask* provides a really simple way to give feedback to a user with the `flashing system`  

The `Flashing system` basically makes it possible to record a message at the end of a request and access it on the next (and only next) request.  
This is usually combined with a *layout template* to expose the message :)

- To flash a message use the `flash()` method <https://flask.palletsprojects.com/en/2.3.x/api/#flask.flash>  
- To get hold of the messages you can use  `get_flashed_messages()` which is also available in the templates :)  

See *Message Flashing* for a full example <https://flask.palletsprojects.com/en/2.3.x/patterns/flashing/>

## Sessions

In addition to the `request object` there is also a second object called `session` which allows you to store information specific to a user from one  
request to the next. This is implemented on top of `cookies` for you and signs the *cookies* cyrptographically. What this means is tha the user could  
look at the contents of your cookie but not to modify it, unless they know the secret key used for signing.

In order to use `sessions` you have to set a secret key. Here is how sessions work:  

> How to generate goog secret keys:  
A secret key should be as random as possible. Your operating system has ways to generate pretty random data based on a cryptographic random  
generator. Use the following command to quickly generate a value for `Flask.secret_key` (or `SECRET_KEY`)

```bash
python -c 'import secrets; print(secrets.token_hex())'
190bb67f2a4199b0facf1f300786cd2f4233ad52cc7df27288b68f4a9a86721a
```

> A note on `cookie-based sessions`. Flask will take the values you put into the `session object` and serialize them into a `cookie`. If you are finding some  
values do not persist across requests, cookies are indeed enabled, and you are getting a clear error message, check the size of the cookie in you page  
responses compared to the size supported by web browsers  

Besides the default client-side based sessions, if you want to handle sessions on the server-side instead, there are several Flask extensions that support this  
Additional (rack it up!)

## APIs with JSON

A common response format when writing an API is `JSON`. It is easy to get started writing such an API with Flask. If you return a `dict` or `list` from a view,  
it will be converted to a JSON response

```python
@app.route("/me")
def me_api():
    user = get_current_user()
    return {
            "username": user.username,
            "theme": user.theme,
            "image": url_for("user_image", filename=user.image),
    }

@app.route("/users")
def users_api():
    users = get_all_users()
    return [user.to_json() for user in users] # What is the alternative programming abstraction to loops in Python
```
This is a *shortcut* (What is the long cut? : Probabbly a JSON parser OS tool) to passing the data to the `jsonify` funtion, (oh! jsonify), which serializes any  
supported-JSON data type. That means that all the data in the dict or list must be JSON serializable

For complex types such as database models, you will want to use a serialization library to convert data to valid JSON types first. There are many serialization  
librarues and Flask API extensions maintained by the community that supports more complex applications

## About Responses

The return value from a view funtion is automatically converted into a `response object` for you :) If the return value is a string it's converted into a  
`response object` with the `string` as `response body`, a `200 OK` status code and a `text/html` MIMETYPE.

If the return value is a `dict` or a `list`, `jsonify()` is called to produce a `response`  
The logic that Flask applies to converting return values into response objects is as follows:  

1. If a `response object` of the `correct type` is returned it's *directly returned* from the `view`
2. If it is a `string`, a `response object` is created with `data` and the `default parameters`
3. If it is an `iterator` or `generator` returning `strings or bytes`, it is treated as a `streaming response`
4. If it is a `dict` or `list`, a response object is created using `jsonify()`
5. If a `tuple` is returned the *items in the tuple can provide extra information*. Such tuples have to be in the form  
`(response, status)`, `(response, headers)`, `(response, status, headers)`. The `status` value will override the *status code* and `headers` can be a list or  
dictionary of additional header values  
6. If none of that works. Flask will assume the return value is a valid `WSGI` application and convert that into a response object

> If you want to get a hole of the resulting response object inside the view you can use the `make_response()` funtion :)

## Redirects and Errors

To redirect a user to another endpoint, use the `redirect()` funtion; to abort a request early with an error code, use the `abort()` funtion:

{ }

> Note the `404` after the `render_template()` call. This tells Flask that the status code of that page should be 404 which means *not found*  
By default 200 is assumed which translates to: *all went well*

See Handling Application Errors <https://flask.palletsprojects.com/en/2.3.x/errorhandling/> for more details...

## Cookies

To access cookies you can use the `cookies` attribute. To set cookies you can use the `set_cookies` method of response objects. The `cookies` attribute of  
`request objects` is a dictionary but instead use the Sessions <https://flask.palletsprojects.com/en/2.3.x/quickstart/#sessions> in Flask that add some  
security on top of cookies for you :)

{ reading cookies } 
{ storing cookies }

> Note that cookies are set on response objects. *Since you normally just return strings from the view functions*  
Flask will convert them into *response objects* for you :) If you explicitly want to do that you can use the `make_response()` function and the modify it  

Sometimes you might want to set a cookie at a point where the response object does not exist yet. This is possible by utilizing the  
*Deferred Request Callbacks* pattern (lazy loading)

For this also see About Responses <https://flask.palletsprojects.com/en/2.3.x/quickstart/#about-responses> (preceding discussion)

## File Uploads

## The Request Object

The `request object` is documented in the API section and we will not cover it here in detail (see `Request`  
<https://flask.palletsprojects.com/en/2.3.x/api/#flask.Request>)

Here is a broad overview of some of the *most common operations*

First of all you have to import from the `flask` module:

```python
from flask import request
```

## Accessing Request Data

For web applications it's crucial to react to the data a client sends to the server. In Flask this information is provided by the global `request` object.  

If you have some experience with Python you might be wondering how that object can be global and how Flask managed to still be threadsafe. 

The answer is **context locals**:

### Context Locals
> Insider Information  
If you want to uderstand how that works and how you can implement tests with context locals, read this section, otherwise just skip it ::)


