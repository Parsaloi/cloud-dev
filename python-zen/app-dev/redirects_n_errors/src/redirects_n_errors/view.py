# By default a black and white error page is shown for each code. If you want to customize the error page, you can use the 'errorhandler()' decorator

from flask import render_template

@app.errorhandler(404)
def page_not_found(error):
    return render_template('page_not_found.html'), 404

