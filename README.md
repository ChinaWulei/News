Project backend files:
1. Connect Redis to cache hot search data and use null value caching to solve cache penetration issues.
2. Use Redis to store SMS verification codes (with a validity period of 2 minutes). When a user requests to send a verification code, the backend generates a six digit random number as the verification code, and uses' person: login: '+phone number as the key. The verification code is stored as a value in Redis, with a validity period of 120 seconds
3. After the user successfully logs in, a user token is generated and saved in Redis, with a validity period of 30 minutes. During this 30 minute period, if the interceptor intercepts the user's request to access the server's resources, the validity period of the user token is extended back to 30 minutes.
