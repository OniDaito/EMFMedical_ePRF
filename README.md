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

## Nook Emulator that does work

Use the Nook definition file (device.xml in this repository). Import it into Android Studio. Download and install Gingerbread 2.3.3 making sure you get either the ARM or x86 image. You can choose which you prefer though ARM is slower (even if it reflects the real hardware).

You can launch the ePRF fairly easily by pressing the play button inside Android Studio.

## Nook Emulation that doesn't work

The nook page details a method for creating an AVD. I've summarised it here but I've got to the stage where the emulator runs but one cannot connect to the AVD as the USB settings haven't been enabled and the settings program within the Nook Image crashes. Its a shame but it's recommended to go for the 2.3.3 standard android image.

Create an Android Virtual Device (AVD) in the manner you normally would. If you've installed the previous file, the Nook should appear as a Target System, though the physical aspects of the device you will need to specify yourself. I've included a hardware profile but the details are as follows:

* 6 inch screen diagonal
* 600 x 800 Pixels
* 2 Gb of memory
* Has no cameras or sensors
* NOOKTablet skin (optional I guess)

The system image will be marked as deprecated, and it will also be an ARM image which will slow things down a little bit. If you hunt in 'other images' you should find Gingerbread (Deprecated) API Level 10 Android 2.3.3 (NOOK Tablet).

Install the Android 2.3.3 (API10) ARMEABI Kernel Image using the SDK manager.

The emulator won't run straight off - you need to alter the ini file for the avd a little to make sure it has a correct path to the API 10 Kernel. Add the following line to your AVD config file. On the Mac it is likely to live in:

    /Users/oni/.android/avd/Nook_API_10.avd/config.ini

Add the following line:

    image.sysdir.2=platforms/android-10/images/

Readingthe old page, it's not clear if we should be using android-15 for the emulator or Android 10. However, I've found that Android 10 works well. You can even load the skin by setting the following line:

    skin.dynamic=yes
    skin.name=NOOKTablet
    skin.path=/Users/oni/Library/Android/sdk/add-ons/addon-nook_tablet-barnes_and_noble_inc-10/skins/NOOKTablet

*Important* If you edit the emulator inside the Dev Studio, you'll loose the sysdir.2 line and need to add it again manually!

The entire config.xml I have looks like this:

    AvdId=Nook_API_10_2
    PlayStore.enabled=false
    abi.type=armeabi
    avd.ini.displayname=Nook API 10 2
    avd.ini.encoding=UTF-8
    disk.dataPartition.size=800M
    fastboot.chosenSnapshotFile=
    fastboot.forceChosenSnapshotBoot=no
    fastboot.forceColdBoot=no
    fastboot.forceFastBoot=yes
    hw.accelerometer=no
    hw.arc=false
    hw.audioInput=yes
    hw.battery=yes
    hw.camera.back=virtualscene
    hw.camera.front=emulated
    hw.cpu.arch=arm
    hw.cpu.ncore=2
    hw.dPad=no
    hw.device.hash2=MD5:2276c9d08b9db341bab94065dd1a727d
    hw.device.manufacturer=User
    hw.device.name=Nook
    hw.gps=no
    hw.gpu.enabled=no
    hw.gpu.mode=off
    hw.initialOrientation=Portrait
    hw.keyboard=yes
    hw.lcd.density=160
    hw.lcd.height=800
    hw.lcd.width=600
    hw.mainKeys=yes
    hw.ramSize=256
    hw.sdCard=yes
    hw.sensors.orientation=no
    hw.sensors.proximity=no
    hw.trackBall=no
    image.sysdir.1=add-ons/addon-nook_tablet-barnes_and_noble_inc-10/images/
    image.sysdir.2=platforms/android-10/images/
    runtime.network.latency=none
    runtime.network.speed=full
    sdcard.path=/Users/oni/.android/avd/Nook_API_10_2.avd/sdcard.img
    sdcard.size=512 MB
    showDeviceFrame=yes
    skin.dynamic=yes
    skin.name=NOOKTablet
    skin.path=/Users/oni/Library/Android/sdk/add-ons/addon-nook_tablet-barnes_and_noble_inc-10/skins/NOOKTablet
    tag.display=NOOK Tablet
    tag.id=nook_tablet
    vm.heapSize=48

Sadly, there is still more we need to do. We need to turn on ABD developer support within the emulator and unfortunately, that doesn't seem to be possible as the settings application on the simulator crashes.

## Tool Issues

Most of the Android Tools don't work with Java 11. Stick to Java 8 and it'll make life a lot easier!

Things like the command line sdkmanager and avdmanager dont really run well on MacOSX with newer java SDKs. I found I needed to replace line 31 in the sdkmanager with this:

    DEFAULT_JVM_OPTS="-Dcom.android.sdklib.toolsdir=%~dp0\.." -XX:+IgnoreUnrecognizedVMOptions
