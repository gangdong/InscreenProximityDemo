#pragma once
//------------------------------------------------------------------------------
/// \file   libmaxtouch.h
/// \brief  headers for MXT device low level access
/// \author Nick Dyer
//------------------------------------------------------------------------------
// Copyright 2011 Atmel Corporation. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
//    1. Redistributions of source code must retain the above copyright notice,
//    this list of conditions and the following disclaimer.
//
//    2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY ATMEL ''AS IS'' AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
// EVENT SHALL ATMEL OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
// INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
// OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
// LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
// NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
// EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//------------------------------------------------------------------------------

#ifdef __cplusplus
extern "C" {
#endif

#include <stdbool.h>
#include <stddef.h>
#include <signal.h>


int mxt_success();
/*
int mxt_free(struct libmaxtouch_ctx *ctx);
int mxt_scan(struct libmaxtouch_ctx *ctx, struct mxt_conn_info **conn, bool query);
int mxt_new_conn(struct mxt_conn_info **conn, enum mxt_device_type type);
struct mxt_conn_info *mxt_ref_conn(struct mxt_conn_info *conn);
struct mxt_conn_info *mxt_unref_conn(struct mxt_conn_info *conn);
int mxt_new_device(struct libmaxtouch_ctx *ctx, struct mxt_conn_info *conn, struct mxt_device **mxt);
void mxt_set_log_fn(struct libmaxtouch_ctx *ctx, void (*log_fn)(struct libmaxtouch_ctx *ctx, enum mxt_log_level level, const char *format, va_list args));
void mxt_free_device(struct mxt_device *mxt);
int mxt_get_info(struct mxt_device *mxt);
int mxt_read_register(struct mxt_device *mxt, uint8_t *buf, int start_register, int count);
int mxt_write_register(struct mxt_device *mxt, uint8_t const *buf, int start_register, int count);
int mxt_set_debug(struct mxt_device *mxt, bool debug_state);
int mxt_get_debug(struct mxt_device *mxt, bool *value);
int mxt_reset_chip(struct mxt_device *mxt, bool bootloader_mode);
int mxt_calibrate_chip(struct mxt_device *mxt);
int mxt_backup_config(struct mxt_device *mxt, uint8_t backup_command);
int mxt_load_config_file(struct mxt_device *mxt, const char *cfg_file);
int mxt_save_config_file(struct mxt_device *mxt, const char *filename);
int mxt_zero_config(struct mxt_device *mxt);
int mxt_get_msg_count(struct mxt_device *mxt, int *count);
char *mxt_get_msg_string(struct mxt_device *mxt);
int mxt_get_msg_bytes(struct mxt_device *mxt, unsigned char *buf, size_t buflen, int *count);
int mxt_msg_reset(struct mxt_device *mxt);
int mxt_get_msg_poll_fd(struct mxt_device *mxt);
int mxt_bootloader_read(struct mxt_device *mxt, unsigned char *buf, int count);
int mxt_bootloader_write(struct mxt_device *mxt, unsigned char const *buf, int count);
int mxt_msg_wait(struct mxt_device *mxt, int timeout_ms);
int mxt_errno_to_rc(int errno_in);
int mxt_report_all(struct mxt_device *mxt);
*/
#ifdef __cplusplus
}
#endif
