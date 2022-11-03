# A vulnerable app has been deployed on a phone. Develop and push your app next to it to gain the flag !

## Rules

Your app must adhere to the following prerequisites for it to win:

* simple apk, no bundle
* emulator in which the apps will run supports API 31
* limited amount of time to extract the flag
* its package name must be `com.ecw2022.app`
* its main activity must be `MainActivity`
* lean apk: we limit the size of uploaded apk to 10 MB at most

Logcat output will be filtered and only log lines containing {ECW2022} will be sent back to you. In particular, in order to have an idea of what is going on remotely, double check you use this marker correctly.

## Pushing our apk

The docker image exposes a web service with a **single-use** POST-enabled `/apk` route. **You must start your instance again if you want to push a new version of your apk**. The handler expects the content of the request to be formdata, and contains an APK under the `apk` label. `curl` can be used to push the apk, assuming that host:port is your running Docker instance:

```sh
curl -v -F 'apk=@app-debug.apk' http://host:port/apk
*   Trying 127.0.0.1:8080...
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> POST /apk HTTP/1.1
> Host: 127.0.0.1:8080
> User-Agent: curl/7.81.0
> Accept: */*
> Content-Length: 6958864
> Content-Type: multipart/form-data; boundary=------------------------32d9076c3c24ede8
> Expect: 100-continue
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 100 Continue
* We are completely uploaded and fine

# after a while
< HTTP/1.1 200 OK
< Content-Type: application/json; charset=utf-8
< Content-Length: 243
< Date: Thu, 04 Aug 2022 12:00:50 GMT
< Server: Python/3.10 aiohttp/3.8.1
< 
* Connection #0 to host 127.0.0.1 left intact
["virtual device up", "virtual device booted", "installing candidate app", "candidate app installed", "candidate app started", "08-04 12:00:31.421  2586  2586 I {ECW2022}: ECW2022{f48abbddb10de3664a444934de050546}", "virtual device destroyed"]
```

The POST request used to push to apk will be eventually answered, with the list of events that occured during the processing. Real-time notifications are offered over ws using `/events` route.

## Subscribing to real-time notifications

A script based on `aiohttp` allows to pull notifications in real-time, assuming that host:port is your running Docker instance:

```sh
$ HOST=host PORT=port ./ws-consumer.py
>  You have just subscribed to events channel, it will give you realtime feedback on what is happening on android emulator side. If you have not pushed your apk yet, please do it, e.g. curl -F "apk=@app.apk" http://<your docker instance>/apk
>  virtual device up
>  virtual device booted
>  installing candidate app
>  candidate app installed
>  candidate app started
>  08-04 12:00:31.421  2586  2586 I {ECW2022}: ECW2022{XXX}
>  virtual device destroyed
```
