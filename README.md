# EMFMedical ePRF Android app

This is the android side of the [@EMFMedical](https://twitter.com/EMFMedical) program for recording patient records.

This is an Android studio project designed for the Nook E-Reader touch tablets.

## In a nutshell, how do I get this all working?

You'll need a computer capable of running android studio, an internet connection and a nook!

* Install the required software.
* Clone this repository and open it with Android Studio.
* Install the required build-tools, SDKs and such.
* Build the application.
* De-register your nook.
* Root your nook.
* Plug it into the computer with USB and run the ePRF program from android studio.

## Versions and numbers

This is an old device and so it is particular to various versions. You'll get lots of warnings but it should all work so long as you install the right versions of the software:

* API 10 - Gingerbread 2.3.3 (with the Nook addons - see further down the page)
* Build Tools 19.1.0
* Gradle 2.0.0
* Java 8

When installing these things inside Android Studio you may need to uncheck "hide obsolete packages" and "show package details" to get the options for installing.

## Building

Firstly, download a copy of [Android Studio](https://developer.android.com/studio/) and set it up. Building has been tested and works on Linux, MacOSX and Windows, though emulation doesn't appear to work on my Linux system as yet.

With a version of Android Studio installed, hopefully importing this project and hitting build will work. Likely you'll need to install Android 2.3.3 (Gingerbread?) and the usual Android tools first. The Nook instructions used to be available at [https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide#_Install_NOOK_SDK_Addon](https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide#_Install_NOOK_SDK_Addon) however this page no longer exists. A copy can be found on the Wayback Machine at [https://web.archive.org/web/20160329190015/https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide](https://web.archive.org/web/20160329190015/https://nookdeveloper.zendesk.com/entries/21943338-nook-developer-start-up-guide). I have repeated the key issues here:

* NOOK Tablet -- Android 2.3.3 (Gingerbread) - API Level 10
* Specify the SDK Addon site - http://su.barnesandnoble.com/nook/sdk/Nook_Tablet_addon.xml
* Install the Nook addons. This can be found underneath Android 2.3.3 SDK option in the SDK manager (You need to 'view package details')
* Install Android SDK Build-Tools 19.1.0. This is quite an old version but creates correct apk files for the nook.

At this point, you should be able to build the program. You will get warnings about the versions being too old, but it should still compile.

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

Sadly, there is still more we need to do. We need to turn on ADB developer support within the emulator and unfortunately, that doesn't seem to be possible as the settings application on the simulator crashes.

### Future Fix

It might be possible to root this default image and enable abd using the same method used for the physical device but I've not had the time to try this yet.

## The Physical Device

Out of the box, the Nook won't work with the ePRF. We need to do the following things

* Bypass the Barnes and Noble registration
* Root the device to enable adb by default.

### Bypass registration

When you turn the device on, you will get a welcome screen. DO NOT FOLLOW THE PROMPTS! You'll be asked to register and setup wifi and similar. Instead, you should do the following:

* Hold down the right hand button at the top of the nook
* Swipe your finger across the top of the screen from left to right
* A factory button will appear at the bottom of the screen. Press this, letting go of the top right button.
* You will be inside the factory screen. Again, hold the top right button and tap the bottom of the screen. A button marked skip Oobe will appear. Press this.
* Your nook will now bypass the registration

### Rooting the Nook

This process should be done once you have bypassed registration. The idea is to boot off an SDCard and load a set of utilities and turn on adb so we can load our own software and get the ePRF running. To do this, we need to download a disk image, burn it to an SDCard and reboot the nook with the SDCard inside. The instructions for rooting can be found at: [http://forum.xda-developers.com/showthread.php?t=2040351](http://forum.xda-developers.com/showthread.php?t=2040351)

In a nutshell, do the following:

* Get yourself a Micro SDCard. It can be small, 2 Gig is fine.
* Download the following image: [http://download.doozan.com/nook/NookManager-0.5.0.zip](http://download.doozan.com/nook/NookManager-0.5.0.zip)
* Unzip this file and burn it to your SDCard using either 'dd' on Linux or Win32DiskImager or similar
* Turn off your nook and insert the SDCard.
* Power on the nook. You should see the "NookManager" titles.
* You will be asked if you want to use wifi. Say 'No'.
* You can have the option to perform a backup. The above guide recommends it but I don't see the need at this point.
* Navigate to the option that says "Root" then "Root my Device".
* Wait till the rooting is complete then follow the onscreen prompts.

Your Nook should be rooted. You can take out the SDCard and the device should restart.

### Installing the ePRF software from Android Studio

By default, when you plug in the Nook it will be mounted as a USB storage device. However, if you unmount it using 'umount' or the equivalent on the mac, it should be recognised by Android Studio and adb as a USB device ready for testing.

If you have successfully built the application, it should be possible to press the green 'run' button and have the ePRF run directly on the device complete with debugging.

If you receieve any errors double check which version of the build tools you are using. It should be 19.1.0 with Gradle 2.0.0. Newer versions create APKs that are not compatible with the nook.


## Tool Issues

Most of the Android Tools don't work with Java 11. Stick to Java 8 and it'll make life a lot easier!

Things like the command line sdkmanager and avdmanager dont really run well on MacOSX with newer java SDKs. I found I needed to replace line 31 in the sdkmanager with this:

    DEFAULT_JVM_OPTS="-Dcom.android.sdklib.toolsdir=%~dp0\.." -XX:+IgnoreUnrecognizedVMOptions

