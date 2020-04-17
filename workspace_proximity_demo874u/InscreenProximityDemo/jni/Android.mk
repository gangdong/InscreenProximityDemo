LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := MaxtouchJni
#LOCAL_SRC_FILES := TestJin.c \ sub.c \ src/main_sub.c
LOCAL_SRC_FILES := jniinterface.c \
 Libmaxtouch/config.c \
 Libmaxtouch/info_block.c \
 Libmaxtouch/libmaxtouch.c \
 Libmaxtouch/msg.c \
 Libmaxtouch/utilfuncs.c \
 Libmaxtouch/hidraw/hidraw_device.c \
 Libmaxtouch/i2c_dev/i2c_dev_device.c \
 Libmaxtouch/sysfs/dmesg.c \
 Libmaxtouch/sysfs/sysfs_device.c \
 Libmaxtouch/sysfs/sysinfo.c \
 Libmaxtouch/log.c \
LOCAL_LDLIBS := -llog
LOCAL_C_INCLUDES:= $(LOCAL_PATH)
include $(call all-subdir-makefiles)
include $(BUILD_SHARED_LIBRARY)
