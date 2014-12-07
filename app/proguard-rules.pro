##---------------Begin: proguard configuration common for all Android apps ----------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*


-allowaccessmodification
-keepattributes *Annotation*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-repackageclasses ''
-keepnames class * { @butterknife.InjectView *;}


-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends java.io.Serializable
-keep public class com.android.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService


-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}


# Preserve all native method names and the names of their classes.
-keepclasseswithmembernames class * {
    native <methods>;
}


-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}


-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}


# Preserve static fields of inner classes of R classes that might be accessed
# through introspection.
-keepclassmembers class **.R$* {
  public static <fields>;
}


# Preserve the special static methods that are required in all enumeration classes.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-keep public class * {
    public protected *;
}


-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
##---------------End: proguard configuration common for all Android apps ----------


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Otto specific classes
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}


# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }


# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }


##---------------End: proguard configuration for Gson  ----------

##---------------Beginin: proguard configuration for libries  ----------
-dontwarn com.sourcemuse.gradle.plugin.**
-dontwarn com.sun.jna.platform.**
-dontwarn org.xmlpull.v1.XmlPullParser
-dontwarn org.xmlpull.v1.XmlSerializer
-dontwarn com.sun.jna.**
-dontwarn de.flapdoodle.embed.mongo.tests.**
-dontwarn android.support.v4.**
-dontwarn com.squareup.okhttp.**
-dontwarn butterknife.internal.**


-keep class * extends android.support.v4.app.Fragment
-keep class de.hdodenhof.** { *; }
-keep class com.felipecsl.** { *; }
-keep class com.github.amlcurran.showcaseview.** { *; }
-keep class com.google.code.ksoap2-android.** { *; }
-keep class com.sourcemuse.gradle.plugin.** { *; }
-keep class com.github.johnkil.** { *; }
-keep class com.astuetz.** { *; }
-keep class com.github.chrisbanes.photoview.** { *; }
-keep class com.quentindommerc.superlistview.** { *; }
-keep class com.sun.jna.platform.** { *; }
-keep class org.xmlpull.v1.XmlPullParser { *; }
-keep class org.xmlpull.v1.XmlSerializer { *; }
-keep class **$$ViewInjector { *; }
-keep class retrofit.** { *; }
-keep class javax.inject.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class com.viselabs.aquariummanager.util.seneye.SeneyeService { ; }
-keep class com.viselabs.aquariummanager.util.seneye.model.* { ; }

##---------------End: proguard configuration for libries  ----------