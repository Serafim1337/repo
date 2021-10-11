//подключение библиотек
#include <iostream>
#include "afx.h"
#include <winsock2.h>
#include <process.h> /* _beginthread, _endthread */
#include <string.h>
#include <time.h> 


//переменная файлового типа
CFile f;
CFileException ex;
//clock_t start, finish;
using namespace std;
// функция чтения сообщений
char* read(char* p, int n)
{
    char mail[200];
    char* message = new char[200];
    int j = 0;
    for (int i = n; p[i] != ' '; i++)
    {
        mail[j] = p[i];
        j++;
    }

    char fName[200];
    strcpy(fName, mail);
    ////////
//  char message[200];

    f.Open(fName, ios::out);
    if (!f) cout << "Error" << endl;
    f.Read(message, strlen(message));
    f.Close();
    //  cout<<message<<endl;
    //  cout<<"_________________"<<endl;


    return message;
}


int main()
{
    WORD wVersionRequested;
    WSADATA wsaData;
    int err;
    wVersionRequested = MAKEWORD(2, 2);
    err = WSAStartup(wVersionRequested, &wsaData);
    if (err != 0) return -1;

    sockaddr_in local;
    local.sin_family = AF_INET;
    local.sin_port = htons(1280);
    local.sin_addr.s_addr = htonl(INADDR_ANY);
    SOCKET s = socket(AF_INET, SOCK_STREAM, 0);

    int c = bind(s, (struct sockaddr*)&local, sizeof(local));
    int r = listen(s, 5);

    while (true)
    {
        sockaddr_in remote;
        int j = sizeof(remote);
        SOCKET newS = accept(s, (struct sockaddr*)&remote, &j);

        _beginthread(mailworking, 0, (void*)newS);
    }
    WSACleanup();
    return 0;
}