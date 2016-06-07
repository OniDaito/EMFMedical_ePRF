# EMFMedical ePRF Android app

This is the android side of the [@EMFMedical](https://twitter.com/EMFMedical) program for recording patient records.

This is an Android studio project designed for the Nook E-Reader tablets that use Android 2.3 (API7).

## Building

With a version of Android Studio installed, hopefully importing this project and hitting build will work. Likely you'll need to install Android 2.3.3 (Gingerbread?) and the usual Android tools first. The Nook instructions can be found here:

[https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide#_Install_NOOK_SDK_Addon](https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide#_Install_NOOK_SDK_Addon)

There are some support files and an emulator available for testing your applications.

I found this link to be very handy, if you view the source, in order to download API10 which I use for building

[http://qdevarena.blogspot.co.uk/2010/05/download-android-sdk-standalone-for.html](http://qdevarena.blogspot.co.uk/2010/05/download-android-sdk-standalone-for.html)

## Testing

Due to the fact that this targets an old version of android, the tests will only build for a later version of Android (API 10) so these have been commented out by default, though they can be put back in.