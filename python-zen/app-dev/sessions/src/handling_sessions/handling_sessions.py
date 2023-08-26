from flask import session

# Set the secret key to some random bytes, Keep this really secret! Which k8s object? Find out...
app.secret_key = b'_5#y2L"F4Q8z\n\xec]'

app.route('/')
def index():
    if 'username' in session: # What is the alternative to if statemenents in Python...is there some kind of abstraction available?
        return f'Logged in as {session["username"]}'
    return 'Ypu are not logged in'

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        session['username'] = request.form['username']
        return redirect(url_for('index'))
    return '''
      <form method="post">
        <p><input type=text name=username>
        <p><input type=submit value=Login>
      </form>
    '''

@app.route('/logout')
def logout():
    # Remove the username from the session if it's there
    session.pop('username', None)
    return redirect(url_for('index'))

# How to generate goog secret keys:
# A secret key should be as random as possible. Your operating system has ways to generate pretty random data based on a cryptographic random 
# generator. Use the following command to quickly generate a value for `Flask.secret_key` (or SECRET_KEY) 
