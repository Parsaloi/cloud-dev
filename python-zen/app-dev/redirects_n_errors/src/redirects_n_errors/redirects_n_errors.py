from flask import abort, redirect, url_for

@app.route('/')
def index():
    return redirect(url_for('login'))

@app.route('/login')
def login():
    abort(401)
    this_is_never_executed()

# This is a rather pointless example because a user will be redirects from the index to a page they cannot access (401 means access denied) but it shows how it  
works
