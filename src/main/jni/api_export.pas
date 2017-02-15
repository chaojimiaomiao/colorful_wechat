unit api_export;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, jni2, jni_utils, caller, wth_classes;

function Java_com_rarnu_tophighlight_api_WthApi_userRegister(env: PJNIEnv; obj: jobject; account: jstring; password: jstring; nickname: jstring; email: jstring): Integer; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userLogin(env: PJNIEnv; obj: jobject; account: jstring; password: jstring): jobject; stdcall;

implementation


function Java_com_rarnu_tophighlight_api_WthApi_userRegister(env: PJNIEnv;
  obj: jobject; account: jstring; password: jstring; nickname: jstring;
  email: jstring): Integer; stdcall;
begin
  Result := userRegister(
    jstringToString(env, account),
    jstringToString(env, password),
    jstringToString(env, nickname),
    jstringToString(env, email)
  );
end;

function Java_com_rarnu_tophighlight_api_WthApi_userLogin(env: PJNIEnv;
  obj: jobject; account: jstring; password: jstring): jobject; stdcall;
var
  u: User;
begin
  u := userLogin(
    jstringToString(env, account),
    jstringToString(env, password)
  );
  Result := nil;
  if (u <> nil) then Result := u.toJObject(env);
  if (u <> nil) then u.Free;
end;

end.

