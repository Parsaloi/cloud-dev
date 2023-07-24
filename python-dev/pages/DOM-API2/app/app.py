from flask import Flask, flash, redirect, render_template, session, url_for
from wtforms import TextAreaField
from wtforms.validators import DataRequired

from flask_wtf import FlaskForm

DEBUG = True
SECRET_KEY = "secret"

app = Flask(__name__)
app.config.from_object(__name__)


class CommentForm(FlaskForm):
    comment = TextAreaField("Comment", validators=[DataRequired()])

@app.route("/")
def index(form=None):
    if form is None:
        form = CommentForm()
    comments = session.get("comments", [])
    return render_template("index.html", comments=comments, form=form)

@app.route("/add/", methods=("POST",))
def add_comment():
    form = CommentForm()
    if form.validate_on_submit():
        comments = session.pop("comments", [])
        comments.append(form.comment.data)
        session["comments"] = comments
        flash("You have added a new comment")
        return redirect(url_for("index"))
    return index(form)

if __name__ == '__main__':
    app.run()
