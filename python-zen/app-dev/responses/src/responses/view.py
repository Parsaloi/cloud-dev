# Imagine you have a view like this:

from flask import render_template

@app.errorhandler(404)
def not_found(error):
    return render_template('error.html'), 404
