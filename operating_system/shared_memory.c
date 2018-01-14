#include <studio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>


#define BUFSZ 1024

int main(int argc, char *argv[])
{
    int shmid;
    key_t key;

    key = ftok("./", 2015);
    if (key == -1) {
        perror("ftok");
    }

    // 创建共享内存
    shmid = shmget(key, BUFSZ, IPC_CREATE|0666);
    if (shmid < 0) {
        perror("shmget");
        exit(-1);
    }
    return 0;
}
