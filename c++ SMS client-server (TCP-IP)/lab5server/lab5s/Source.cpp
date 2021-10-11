// server.cpp: определяет точку входа для консольного приложения. 
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS

#ifdef _DEBUG 
#define new DEBUG_NEW 
#endif 

//#define _AFXDLL 
#include <iostream> 
#include "stdafx.h" 
#include "afx.h" 
#include <winsock2.h> 
#include <process.h> /* _beginthread, _endthread */ 
#include <string.h> 
#include <time.h> 
#include "atlstr.h" 
#include <windows.h> 
#include <vector> 
#include <math.h> 

using namespace std;
CFile f;
CFileException ex;
int getInbox(const char* searchkey, vector<wstring>& list) {

	WIN32_FIND_DATA fd;
	CString tmpSearchKey(searchkey);
	HANDLE h = FindFirstFile(tmpSearchKey, &fd);

	if (h == INVALID_HANDLE_VALUE)
	{
		return 0; // no files found 
	}

	while (1)
	{
		std::wstring tmpFileName = fd.cFileName;
		list.push_back(tmpFileName);

		if (FindNextFile(h, &fd) == FALSE)
			break;
	}
	return list.size();
}
int registerUser(char* user, char* password) {
	string dirNameStr(user);
	bool t = CreateDirectoryA(dirNameStr.c_str(), NULL);
	if (ERROR_ALREADY_EXISTS == GetLastError()) {
		return 0;
	}
	string inboxDir(dirNameStr + "\\inbox");
	bool t1 = CreateDirectoryA(inboxDir.c_str(), NULL);
	string passFileStr(dirNameStr + "\\password");
	TCHAR* passFile = new TCHAR[passFileStr.size() + 1]; // преобразование типа string в tchar 
	passFile[passFileStr.size()] = 0;
	std::copy(passFileStr.begin(), passFileStr.end(), passFile); // непосредственно преобразование 
	if (!f.Open(passFile, CFile::modeWrite | CFile::modeCreate, &ex)) {
		cerr << "Cannot create password file. Try again\n";
		exit(EXIT_FAILURE);
	}
	f.Write(password, strlen(password));
	f.Close();
	return 1;
}
bool dirExists(const string& dirName_in)
{
	DWORD ftyp = GetFileAttributesA(dirName_in.c_str());
	if (ftyp == INVALID_FILE_ATTRIBUTES)
		return false; //something is wrong with your path! 

	if (ftyp & FILE_ATTRIBUTE_DIRECTORY)
		return true; // this is a directory! 

	return false; // this is not a directory! 
}
int loginUser(char* user, char* password) {
	string dirNameStr(user);
	if (!dirExists(dirNameStr)) {
		return 0;
	}
	string passFileStr(dirNameStr + "\\password");
	TCHAR* passFile = new TCHAR[passFileStr.size() + 1]; // преобразование типа string в tchar 
	passFile[passFileStr.size()] = 0;
	std::copy(passFileStr.begin(), passFileStr.end(), passFile); // непосредственно преобразование 
	if (!f.Open(passFile, CFile::modeRead, &ex)) {
		cerr << "Cannot create password file. Try again\n";
		exit(EXIT_FAILURE);
	}
	char* oldPass = new char[20];
	f.Read(oldPass, strlen(password));
	f.Close();
	oldPass[strlen(password)] = '\0';
	if (!strcmp(password, oldPass)) {
		return 2;
	}
	return 1;
}
void EmailProcess(void* newS) {
	int c;
	char p[200], com[200], num[4];
	char* curU;
	com[0] = '\0'; p[0] = '\0';
	strcat(p, "EMail center connected...\n");
	send((SOCKET)newS, p, sizeof(p), 0);
	while ((c = recv((SOCKET)newS, p, sizeof(p), 0) != 0)) {
		int i = 0;
		while (p[i] != ' ') {
			com[i] = p[i];
			i++;
		};
		com[i] = '\0'; i++;
		if (!strcmp(com, "register") || !strcmp(com, "login")) {
			char user[200], password[200];
			char* message;
			int j = 0;
			for (; p[i] != ' '; i++) {
				user[j] = p[i];
				j++;
			}
			user[j] = '\0';
			i++; j = 0;
			for (; p[i]; i++) {
				password[j] = p[i];
				j++;
			}
			password[j] = '\0';
			if (!strcmp(com, "register")) {
				int result = registerUser(user, password);
				switch (result) {
				case 0:
					message = "Such user doesn't exist";
					break;
				case 1:
					message = "Registration complete";
					cout << "User " << user << " register" << endl;
					curU = user;
					break;
				}
			}
			if (!strcmp(com, "login")) {
				int result = loginUser(user, password);
				switch (result) {
				case 0:
					message = "Incorrect login";
					break;
				case 1:
					message = "Incorrect password";
					break;
				case 2:
					message = "Login success";
					curU = user;
					cout << "User " << user << " login" << endl;
					break;
				}
			}
			send((SOCKET)newS, message + '\0', strlen(message) + 1, 0);
		}
		if (!strcmp(com, "inbox")) {
			vector<wstring> list;
			char* result = new char[17];
			TCHAR* number = new TCHAR[4];
			wstring emails = _T("Your inbox: \n");
			char* templateAddr = new char[strlen(curU)];
			memcpy(templateAddr, curU, strlen(curU));
			templateAddr[strlen(curU)] = '\0';
			strcat(templateAddr, "\\inbox\\*.txt");
			int count = getInbox(templateAddr, list);
			for (int i = 0; i < count; i++) {
				_itow(i + 1, number, 10);
				emails = emails + number + _T(". ") + list[i].c_str();
				emails += '\n';
			}

			CharToOem(emails.c_str(), result);
			send((SOCKET)newS, result + '\0', strlen(result) + 1, 0);
		}
		if (!strcmp(com, "read")) {
			vector<wstring> list;
			char* result;
			char* templateAddr = new char[strlen(curU)];
			memcpy(templateAddr, curU, strlen(curU));
			templateAddr[strlen(curU)] = '\0';
			strcat(templateAddr, "\\inbox\\*.txt");
			int count = getInbox(templateAddr, list);
			int j = 0;
			while (p[i] != '\0') {
				num[j] = p[i];
				i++;
				j++;
			};
			int readEmail = atoi(num);

			if (readEmail <= count) {
				char inboxDirAddrF[200];
				char* inboxDirAddr = new char[strlen(curU)];
				memcpy(inboxDirAddr, curU, strlen(curU));
				inboxDirAddr[strlen(curU)] = '\0';
				strcat(inboxDirAddr, "\\inbox\"); 
					std::wstring tmpp = list[readEmail - 1].c_str();

				char restmp[200];
				CharToOem(tmpp.c_str(), restmp);
				strcat(inboxDirAddr, restmp);
				CString tmpp1;
				tmpp1 = inboxDirAddr;

				if (f.Open(tmpp1, CFile::modeRead)) {
					long iFileSiz = f.GetLength();
					result = new char[iFileSiz];

					f.Read(result, iFileSiz);
					result[iFileSiz] = '\0';
					cout << result << ". Email successfully deleted" << endl;
					f.Close();
				}
			}
			else {
				result = "Incorrect number";
			}
			send((SOCKET)newS, result + '\0', strlen(result) + 1, 0);
		}

		//send 
		if (!strcmp(com, "send")) {
			char* result;

			char templateAddrF[200];
			char user[200];
			char* message;
			int j = 0;
			for (; p[i] != ' '; i++) {
				user[j] = p[i];
				j++;
			}
			user[j] = '\0';
			i++; j = 0;
			message = &p[i];

			vector<wstring> list;
			char* templateAddr = new char[strlen(user)];
			memcpy(templateAddr, user, strlen(user));
			templateAddr[strlen(user)] = '\0';
			strcat(templateAddr, "\\inbox\"); 
				strcat(templateAddr, curU);
			strcpy(templateAddrF, templateAddr);
			strcat(templateAddrF, "*.txt");

			int count = getInbox(templateAddrF, list);

			char* number = new char[4];
			itoa(count + 1, number, 10);
			strcat(templateAddr, number);
			strcat(templateAddr, ".txt");
			delete[]number;

			CString tmpp1;
			tmpp1 = templateAddr;

			if (f.Open(tmpp1, CFile::modeWrite | CFile::modeCreate)) {

				f.Write(message, strlen(message));
				result = message;
				cout << result << ". Email successfully send" << endl;
				f.Close();
			}
			else {
				result = "Incorrect number";
			}
			send((SOCKET)newS, result + '\0', strlen(result) + 1, 0);
		}

		if (!strcmp(com, "quit")) {
			closesocket((SOCKET)newS);
			exit(EXIT_SUCCESS);
			com[0] = '\0';
		}
	}
}

int main() {
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

	while (true) {
		sockaddr_in remote;
		int j = sizeof(remote);
		SOCKET newS = accept(s, (struct sockaddr*)&remote, &j);

		_beginthread(EmailProcess, 0, (void*)newS);
	}
	WSACleanup();
	return 0;
}