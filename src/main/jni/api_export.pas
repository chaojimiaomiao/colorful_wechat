unit api_export;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, JNI2, caller, wth_classes, math, android;

// user
function Java_com_rarnu_tophighlight_api_WthApi_userRegister(env: PJNIEnv; obj: jobject; account: jstring; password: jstring; nickname: jstring; email: jstring): jint; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userLogin(env: PJNIEnv; obj: jobject; account: jstring; password: jstring): jobject; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userValidateEmail(env: PJNIEnv; obj: jobject; account: jstring; email: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userForgetPassword(env: PJNIEnv; obj: jobject; account: jstring; email: jstring; newPassword: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userChangePassword(env: PJNIEnv; obj: jobject; account: jstring; oldPassword: jstring; newPassword: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userChangeInfo(env: PJNIEnv; obj: jobject; id: jint; nickname: jstring; email: jstring; comment: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userUploadHead(env: PJNIEnv; obj: jobject; id: jint; head: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_userGetInfo(env: PJNIEnv; obj: jobject; id: jint): jobject; stdcall;

// theme
function Java_com_rarnu_tophighlight_api_WthApi_themeAddStar(env: PJNIEnv; obj: jobject; id: jint; user: jint): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeRemoveStar(env: PJNIEnv; obj: jobject; id: jint; user: jint): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeUpload(env: PJNIEnv; obj: jobject; author: jint; name: jstring; description: jstring; themeFile: jstring): jint; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeChangeFile(env: PJNIEnv; obj: jobject; id: jint; author: jint; themeFile: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeRemove(env: PJNIEnv; obj: jobject; id: Integer; author: Integer): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeChangeInfo(env: PJNIEnv; obj: jobject; id: jint; author: jint; name: jstring; description: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeGetInfo(env: PJNIEnv; obj: jobject; id: jint; user: jint): jobject; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeGetDownloadUrl(env: PJNIEnv; obj: jobject; id: jint): jstring; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeGetList(env: PJNIEnv; obj: jobject; page: jint; pageSize: jint; sort: jstring): jobject; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_themeGetListByUser(env: PJNIEnv; obj: jobject; page: jint; pageSize: jint; author: jint; sort: jstring): jobject; stdcall;

// comment
function Java_com_rarnu_tophighlight_api_WthApi_commentAdd(env: PJNIEnv; obj: jobject; id: jint; author: jint; comment: jstring): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_commentRemove(env: PJNIEnv; obj: jobject; id: jint; author: jint): jboolean; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_commentGetList(env: PJNIEnv; obj: jobject; id: jint): jobject; stdcall;

// theme file
function Java_com_rarnu_tophighlight_api_WthApi_readThemeFromINI(env: PJNIEnv; obj: jobject; themeFile: jstring): jobject; stdcall;
function Java_com_rarnu_tophighlight_api_WthApi_writeThemeToINI(env: PJNIEnv; obj: jobject; themeFile: jstring; theme: jobject): jboolean; stdcall;

// UUID
procedure Java_com_rarnu_tophighlight_api_WthApi_recordDevice(env: PJNIEnv; obj: jobject); stdcall;

implementation


function Java_com_rarnu_tophighlight_api_WthApi_userRegister(env: PJNIEnv;
  obj: jobject; account: jstring; password: jstring; nickname: jstring;
  email: jstring): jint; stdcall;
begin
  Result := userRegister(
    TJNIEnv.jstringToString(env, account),
    TJNIEnv.jstringToString(env, password),
    TJNIEnv.jstringToString(env, nickname),
    TJNIEnv.jstringToString(env, email)
  );
end;

function Java_com_rarnu_tophighlight_api_WthApi_userLogin(env: PJNIEnv;
  obj: jobject; account: jstring; password: jstring): jobject; stdcall;
var
  u: User;
begin
  u := userLogin(
    TJNIEnv.jstringToString(env, account),
    TJNIEnv.jstringToString(env, password)
  );
  Result := nil;
  if (u <> nil) then Result := u.toJObject(env);
  if (u <> nil) then u.Free;
end;

function Java_com_rarnu_tophighlight_api_WthApi_userValidateEmail(env: PJNIEnv;
  obj: jobject; account: jstring; email: jstring): jboolean; stdcall;
var
  b: Boolean;
begin
  b := userValidateEmail(
    TJNIEnv.jstringToString(env, account),
    TJNIEnv.jstringToString(env, email)
  );
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_userForgetPassword(
  env: PJNIEnv; obj: jobject; account: jstring; email: jstring;
  newPassword: jstring): jboolean; stdcall;
var
  b: Boolean;
begin
  b := userForgetPassword(
    TJNIEnv.jstringToString(env, account),
    TJNIEnv.jstringToString(env, email),
    TJNIEnv.jstringToString(env, newPassword)
  );
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_userChangePassword(
  env: PJNIEnv; obj: jobject; account: jstring; oldPassword: jstring;
  newPassword: jstring): jboolean; stdcall;
var
  b: Boolean;
begin
  b := userChangePassword(
    TJNIEnv.jstringToString(env, account),
    TJNIEnv.jstringToString(env, oldPassword),
    TJNIEnv.jstringToString(env, newPassword)
  );
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_userChangeInfo(env: PJNIEnv;
  obj: jobject; id: jint; nickname: jstring; email: jstring; comment: jstring
  ): jboolean; stdcall;
var
  b: Boolean;
begin
  b := userChangeInfo(
    id,
    TJNIEnv.jstringToString(env, nickname),
    TJNIEnv.jstringToString(env, email),
    TJNIEnv.jstringToString(env, comment)
  );
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_userUploadHead(env: PJNIEnv;
  obj: jobject; id: jint; head: jstring): jboolean; stdcall;
var
  b: Boolean;
begin
  b := userUploadHead(
    id,
    TJNIEnv.jstringToString(env, head)
  );
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_userGetInfo(env: PJNIEnv;
  obj: jobject; id: jint): jobject; stdcall;
var
  u: User;
begin
  u := userGetInfo(id);
  Result := nil;
  if (u <> nil) then Result := u.toJObject(env);
  if (u <> nil) then u.Free;
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeAddStar(env: PJNIEnv;
  obj: jobject; id: jint; user: jint): jboolean; stdcall;
var
  b: Boolean;
begin
  b := themeAddStar(id, user);
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeRemoveStar(env: PJNIEnv;
  obj: jobject; id: jint; user: jint): jboolean; stdcall;
var
  b: Boolean;
begin
  b := themeRemoveStar(id, user);
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeUpload(env: PJNIEnv;
  obj: jobject; author: jint; name: jstring; description: jstring;
  themeFile: jstring): jint; stdcall;
begin
  Result := themeUpload(
    author,
    TJNIEnv.jstringToString(env, name),
    TJNIEnv.jstringToString(env, description),
    TJNIEnv.jstringToString(env, themeFile)
  );
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeChangeFile(env: PJNIEnv;
  obj: jobject; id: jint; author: jint; themeFile: jstring): jboolean; stdcall;
var
  b: Boolean;
begin
  b := themeChangeFile(id, author, TJNIEnv.jstringToString(env, themeFile));
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeRemove(env: PJNIEnv;
  obj: jobject; id: Integer; author: Integer): jboolean; stdcall;
var
  b: Boolean;
begin
  b := themeRemove(id, author);
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeChangeInfo(env: PJNIEnv;
  obj: jobject; id: jint; author: jint; name: jstring; description: jstring
  ): jboolean; stdcall;
var
  b: Boolean;
begin
  b := themeChangeInfo(id, author,
    TJNIEnv.jstringToString(env, name),
    TJNIEnv.jstringToString(env, description));
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeGetInfo(env: PJNIEnv;
  obj: jobject; id: jint; user: jint): jobject; stdcall;
var
  t: Theme;
begin
  t := themeGetInfo(id, user);
  Result := nil;
  if (t <> nil) then Result := t.toJObject(env);
  if (t <> nil) then t.Free;
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeGetDownloadUrl(
  env: PJNIEnv; obj: jobject; id: jint): jstring; stdcall;
var
  s: string;
begin
  s := themeGetDownloadUrl(id);
  Result := TJNIEnv.stringToJString(env, s);
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeGetList(env: PJNIEnv;
  obj: jobject; page: jint; pageSize: jint; sort: jstring): jobject;
var
  list: TThemeList;
begin
  list := themeGetList(page, pageSize, TJNIEnv.jstringToString(env, sort));
  Result := nil;
  if (list <> nil) then Result := ThemeListToJobject(env, list);
  if (list <> nil) then list.Free;
end;

function Java_com_rarnu_tophighlight_api_WthApi_themeGetListByUser(
  env: PJNIEnv; obj: jobject; page: jint; pageSize: jint; author: jint;
  sort: jstring): jobject; stdcall;
var
  list: TThemeList;
begin
  list := themeGetListByUser(page, pageSize, author, TJNIEnv.jstringToString(env, sort));
  Result := nil;
  if (list <> nil) then Result := ThemeListToJobject(env, list);
  if (list <> nil) then list.Free;
end;

function Java_com_rarnu_tophighlight_api_WthApi_commentAdd(env: PJNIEnv;
  obj: jobject; id: jint; author: jint; comment: jstring): jboolean; stdcall;
var
  b: Boolean;
begin
  b := commentAdd(id, author, TJNIEnv.jstringToString(env, comment));
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_commentRemove(env: PJNIEnv;
  obj: jobject; id: jint; author: jint): jboolean; stdcall;
var
  b: Boolean;
begin
  b := commentRemove(id, author);
  Result := ifthen(b, JNI_TRUE, JNI_FALSE);
end;

function Java_com_rarnu_tophighlight_api_WthApi_commentGetList(env: PJNIEnv;
  obj: jobject; id: jint): jobject; stdcall;
var
  list: TCommentList;
begin
  list := commentGetList(id);
  Result := nil;
  if (list <> nil) then Result := CommentListToJObject(env, list);
  if (list <> nil) then list.Free;
end;

function Java_com_rarnu_tophighlight_api_WthApi_readThemeFromINI(env: PJNIEnv;
  obj: jobject; themeFile: jstring): jobject; stdcall;
var
  ti: ThemeIni;
begin
  Result := nil;
  ti := ThemeIni.fromINI(TJNIEnv.jstringToString(env, themeFile));
  if (ti <> nil) then Result := ti.toJObject(env);
end;

function Java_com_rarnu_tophighlight_api_WthApi_writeThemeToINI(env: PJNIEnv;
  obj: jobject; themeFile: jstring; theme: jobject): jboolean; stdcall;
var
  ti: ThemeIni;
begin
  Result := JNI_FALSE;
  ti := ThemeIni.fromJObject(env, theme);
  if (ti <> nil) then Result := ifthen(ti.toINI(TJNIEnv.jstringToString(env, themeFile)), JNI_TRUE, JNI_FALSE);
end;

procedure Java_com_rarnu_tophighlight_api_WthApi_recordDevice(env: PJNIEnv;
  obj: jobject); stdcall;
var
  path: string;
  utxt: string = '';
  dt: TDateTime;
  y, m, d, h, mm, s, ss: Word;
begin
  LOGE('Record Device Start');
  // record uuid
  dt := Now;
  DecodeDate(dt, y, m, d);
  DecodeTime(dt, h, mm, s, ss);
  path := 'data/data/com.rarnu.tophighlight/uuid';
  if (FileExists(path)) then begin
    with TStringList.Create do begin
      LoadFromFile(path);
      utxt:= Text;
      Free;
    end;
  end else begin
    utxt := TGuid.NewGuid.ToString();
    with TStringList.Create do begin
      Text:= utxt;
      SaveToFile(path);
      Free;
    end;
  end;
  utxt:= utxt.Trim;
  LOGE(PChar(Format('Record Device (%d, %d, %d, %d, %d, %s)', [y, m, d, h, mm, utxt])));
  if (utxt <> '') then uuidAdd(y, m, d, h, mm, utxt);
  LOGE('Record Device End');
end;

end.

