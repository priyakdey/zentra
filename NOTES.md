- **Secure Token exchange**:
    
    We were sending token in the response body and storing in the `localStorage`. This makes
    the website vulnerable to XSS, since any 3rd party script can read the token using
    `document.localStorage.get(...)` and send requests back to backend impersonating the logged in user.
    Sending it as a secure cookie, as `Set-Cookie` in the response, makes the browser store the cookie and send it with all API requests automatically. Since it is httponly, JS will have no
    access to the token and hence not vulnerable to stealing. 

    - `httponly`: prevents JS access
    - `secure`:  means only cookie send over https only
    - `SameSite`: Limits cross-origin requests for CSRF protection
    - `Max-Age`: Max time for storing the cookie
    - `Path`: Scopes cookie to specific URL paths
