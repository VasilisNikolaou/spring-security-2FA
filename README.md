# Spring Security Two Factor Authentication

Today's modern web applications require an extra security layer.
This project uses the classic *username/password* authentication (first factor)
and *one time password (OTP)* authentication (second factor).
[Seperation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns) is a popular design principle
so i decided to make two different servers talking with each other via HTTP.
For this repository seperating the logic in two servers might be an overkill but i think it's fine for testing purposes.

# What I Used

* Spring Security 
* Twilio API (for sending the OTP in user's phone)

# The Bigger Picture

![First Factor](/images/1stphase.png)

