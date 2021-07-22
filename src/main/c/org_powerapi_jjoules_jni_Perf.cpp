#include "org_powerapi_jjoules_jni_Perf.h"

#include <asm/unistd.h>
#include <linux/perf_event.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ioctl.h>
#include <unistd.h>
#include <inttypes.h>
#include <iostream>
#include <ostream>

static long
perf_event_open(struct perf_event_attr *hw_event, pid_t pid,
                int cpu, int group_fd, unsigned long flags)
{
    int ret;
    ret = syscall(__NR_perf_event_open, hw_event, pid, cpu,
                  group_fd, flags);
    return ret;
}

#define nb_configs 6
const int counters_type[nb_configs] = {
    PERF_COUNT_HW_INSTRUCTIONS,
    PERF_COUNT_HW_CPU_CYCLES,
    PERF_COUNT_HW_CACHE_REFERENCES,
    PERF_COUNT_HW_CACHE_MISSES,
    PERF_COUNT_HW_BRANCH_INSTRUCTIONS,
    PERF_COUNT_HW_BRANCH_MISSES,
};
const char* labels_counters[nb_configs] = {
    "instructions",
    "cycles",
    "cache-reference",
    "cache-misses",
    "branches",
    "branch-misses",
};

// cache-references,cache-misses,cycles,instructions,branches,branch-misses
long long* counters;
int* fds;
struct perf_event_attr* pe_structs;

JNIEXPORT void JNICALL Java_org_powerapi_jjoules_jni_Perf_start(JNIEnv *env, jobject thisObject)
{
    counters = (long long*)malloc(nb_configs * sizeof(long long));
    fds = (int*)malloc(nb_configs * sizeof(int));
    pe_structs = (struct perf_event_attr*)malloc(nb_configs * sizeof(struct perf_event_attr));

    for (int i = 0; i < nb_configs ; i++) {
        counters[i] = 0;
        memset(&pe_structs[i], 0, sizeof(pe_structs[i]));
        pe_structs[i].type = PERF_TYPE_HARDWARE;
        pe_structs[i].size = sizeof(pe_structs[i]);
        pe_structs[i].config = counters_type[i];
        pe_structs[i].disabled = 1;
        pe_structs[i].exclude_kernel = 1;
        pe_structs[i].exclude_hv = 1;
        fds[i] = perf_event_open(&pe_structs[i], 0, -1, -1, 0);
        if (fds[i] == -1) {
            fprintf(stderr, "Error opening leader %llx\n", pe_structs[i].config);
            exit(EXIT_FAILURE);
        }
        ioctl(fds[i], PERF_EVENT_IOC_RESET, 0);
    }
    for (int i = 0; i < nb_configs ; i++) {
        ioctl(fds[i], PERF_EVENT_IOC_ENABLE, 0);
    }
}

JNIEXPORT jlongArray JNICALL Java_org_powerapi_jjoules_jni_Perf_stop(JNIEnv *env, jobject thisObject)
{
    for (int i = 0; i < nb_configs ; i++) {
        ioctl(fds[i], PERF_EVENT_IOC_DISABLE, 0);
    }
    for (int i = 0; i < nb_configs ; i++) {
        read(fds[i], &counters[i], sizeof(counters[i]));
        printf("Used %lld %s\n", counters[i], labels_counters[i]);
        close(fds[i]);
    }
    jlong* args = new jlong[nb_configs];
    for (int i = 0; i < nb_configs ; i++) {
        args[i] = (long) counters[i];
    }
    jlongArray collectedValues = env->NewLongArray(nb_configs);
    env->SetLongArrayRegion(collectedValues, 0, nb_configs, args);
    return collectedValues;
}

JNIEXPORT jlongArray JNICALL Java_org_powerapi_jjoules_jni_Perf_read(JNIEnv *env, jobject thisObject)
{
    for (int i = 0; i < nb_configs ; i++) {
        read(fds[i], &counters[i], sizeof(counters[i]));
    }
    jlong* args = new jlong[nb_configs];
    for (int i = 0; i < nb_configs ; i++) {
        args[i] = (long) counters[i];
    }
    jlongArray collectedValues = env->NewLongArray(nb_configs);
    env->SetLongArrayRegion(collectedValues, 0, nb_configs, args);
    return collectedValues;
}