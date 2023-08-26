# You just need to wrap the return expression with 'make_response()' and 'get' the 'response object' to modify it, then return it:  

from flask import make_response

@app.errorhandler(404)
def not_found(error):
    resp = make_response(render_template('error.html'), 404)
    resp.headers['X-Something'] = 'A value'
    return resp
