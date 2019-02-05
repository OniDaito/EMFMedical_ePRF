# EMFMedical ePRF Android app

This is the android side of the [@EMFMedical](https://twitter.com/EMFMedical) program for recording patient records.

This is an Android studio project designed for the Nook E-Reader tablets that use Android 2.3 (API7).

## Building

Firstly, download a copy of [Android Studio]() and set it up. Building has been tested and works on Linux, MacOSX and Windows, though emulation doesn't appear to work on my Linux system as yet.

With a version of Android Studio installed, hopefully importing this project and hitting build will work. Likely you'll need to install Android 2.3.3 (Gingerbread?) and the usual Android tools first. The Nook instructions used to be available at [https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide#_Install_NOOK_SDK_Addon](https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide#_Install_NOOK_SDK_Addon) however this page no longer exists. A copy can be found on the Wayback Machine at [https://web.archive.org/web/20160329190015/https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide](https://web.archive.org/web/20160329190015/https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide). I have repeated the key issues here:

* NOOK Tablet -- Android 2.3.3 (Gingerbread) - API Level 10
* Specify the SDK Addon site - http://su.barnesandnoble.com/nook/sdk/Nook_Tablet_addon.xml
* Install the Nook addons. This can be found underneath Android 2.3.3 SDK option in the SDK manager (You need to 'view package details')
* Install Android SDK Build-Tools 23.0.3

At this point, you should be able to build the program. You will get warnings about the versions being too old, but it should still compile.

It's not clear how long the Barnes and Noble xml file and Nook Tablet Addon will be around for. For that reason, I have mirrored the XML and file at [http://archive.section9.co.uk/eprf/Nook_Tablet_addon.xml](http://archive.section9.co.uk/eprf/Nook_Tablet_addon.xml).


I found this link to be very handy, if you view the source, in order to download API10 which I use for building

[http://qdevarena.blogspot.co.uk/2010/05/download-android-sdk-standalone-for.html](http://qdevarena.blogspot.co.uk/2010/05/download-android-sdk-standalone-for.html)

## Emulation and testing

I've so far not managed to get an emulator to run correctly on Linux, though this is perhaps down to an incorrect setting within my bash profile. Regardless, one needs to setup an emulator to test the progam. The website above describes this process but I have summarised the key points:

Create an Android Virtual Device (AVD) in the manner you normally would. If you've installed the previous file, the Nook should appear as a Target System, though the physical aspects of the device you will need to specify yourself. I've included a hardware profile but the details are as follows:

* 6 inch screen diagonal
* 600 x 800 Pixels
* 2 Gb of memory
* Has no cameras or sensors
* NOOKTablet skin (optional I guess)

The system image will be marked as deprecated, and it will also be an ARM image which will slow things down a little bit. If you hunt in 'other images' you should find Gingerbread (Deprecated) API Level 10 Android 2.3.3 (NOOK Tablet).

Install the Android 4.0.3 (API15) ARMEABI Kernel Image using the SDK manager.

The emulator won't run straight off - you need to alter the ini file for the avd a little to make sure it has a correct path to the API 15 Kernel. Add the following line to your AVD config file. On the Mac it is likely to live in:

    /Users/oni/.android/avd/Nook_API_10.avd/config.ini

Add the following line:

    image.sysdir.2=system-images/android-15/default/armeabi-v7a/


