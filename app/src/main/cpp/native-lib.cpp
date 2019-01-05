#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_phone_rest_yuyu_mydemoapplication_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
