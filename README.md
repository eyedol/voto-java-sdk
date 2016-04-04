[![Build Status](https://travis-ci.org/eyedol/voto-java-sdk.svg?branch=develop)](https://travis-ci.org/eyedol/voto-java-sdk) [![Stories in Ready](https://badge.waffle.io/eyedol/voto-java-sdk.png?label=ready&title=Ready)](http://waffle.io/eyedol/voto-java-sdk) [![codecov.io](https://codecov.io/github/eyedol/voto-java-sdk/coverage.svg?branch=develop)](https://codecov.io/github/eyedol/voto-java-sdk?branch=develop) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-voto--java--sdk-green.svg?style=true)](https://android-arsenal.com/details/1/3380)

### VOTO JAVA SDK

VOTO Java SDK is a wrapper around the [VOTO API][1] to make it very easy to use. The goal of this SDK is to ease 
VOTO API integration into both Android and Java applications.

It comes with three independent modules:

- Synchronous module; Access the API synchronously
- Asynchronous module; Access the API asynchronously
- RxJava module; Access the API using RxJava

**Note:** These are not official libraries from the VOTO team.

###<a name="Setup">**Setup**
Add gradle dependency:

Add `jcenter` repostory:
```groovy
repositories {
    jcenter()
}
```

Synchronous module:
```groovy
dependencies {
    compile 'com.addhen:voto-sdk-sync:0.2.0'
}
```

Asynchronous module:
```groovy
dependencies {
    compile 'com.addhen:voto-sdk-async:0.2.0'
}
```

RxJava module:
```groovy
dependencies {
    compile 'com.addhen:voto-sdk-rxjava:0.2.0'
}
```
### Usage
#### GET Data From VOTO API

##### Build API Client
- Synchronous:
```java
SyncVotoApiClient syncVotoApilient = new SyncVotoApiClient.Builder(<api_key>)
	.withLogLevel(HttpLoggingInterceptor.Level.BODY)
	.build();
```
- Asynchronous:
```java
AsyncVotoApiClient asyncVotoClient = new AsyncVotoApiClient.Builder(<api_key>)
	.withLogLevel(HttpLoggingInterceptor.Level.BODY)
	.build();
```

- RxJava:
```java
RxJavaVotoApiClient rxVotoClient = new RxJavaVotoApiClient.Builder(<api_key>)
	.withLogLevel(HttpLoggingInterceptor.Level.BODY)
	.build();
```

##### Execute API Request
- Synchronous:
```java
SyncVotoApiClient syncVotoApiClient = new SyncVotoApiClient.Builder("api_key")
	.withLogLevel(HttpLoggingInterceptor.Level.BODY)
	.build();
	
ListSubscribersResponse listSubscribersResponse = null;
try {
    listSubscribersResponse = syncVotoApiClient.listSubscribers(10);
} catch (IOException e) {
    e.printStackTrace();
}

System.out.println(listSubscribersResponse);
```
- Asynchronous:
```java
AsyncVotoApiClient asyncVotoApiClient = new AsyncVotoApiClient.Builder("api_key")
	.withLogLevel(HttpLoggingInterceptor.Level.BODY)
	.build();
	
asyncVotoApiClient.listSubscribers(10, new Callback<ListSubscribersResponse>() {
	@Override
    public void onResponse(Call<ListSubscribersResponse> call, Response<ListSubscribersResponse> response) {
    ListSubscribersResponse listSubscribersResponse = response.body();
    	System.out.println(listSubscribersResponse);
    }

    @Override
    public void onFailure(Call<ListSubscribersResponse> call, Throwable t) {
    	t.printStackTrace();
    }
});
```

- RxJava:
```java
RxJavaVotoApiClient rxJavaVotoApiClient = new RxJavaVotoApiClient.Builder("api_key")
	.withLogLevel(HttpLoggingInterceptor.Level.BODY)
	.build();
	
rxJavaVotoApiClient.listSubscribers(10).subscribe(new Action1<ListSubscribersResponse>() {
	@Override
	public void call(ListSubscribersResponse listSubscribersResponse) {
    	System.out.println(listSubscribersResponse);
  	}
});
```

License
--------

    Copyright 2016 Henry Addo Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://go.votomobile.org/apidoc/index.html