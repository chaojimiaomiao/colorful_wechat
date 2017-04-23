unit caller;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, http_utils, encrypt_utils, fpjson, jsonparser, jsonscanner, wth_classes, android, JNI2;

const
  BASEURL = 'http://rarnu.xyz/wth/';

// user
function userRegister(account: string; password: string; nickname: string; email: string): Integer;
function userLogin(account: string; password: string): User;
function userValidateEmail(account: string; email: string): Boolean;
function userForgetPassword(account: String; email: String; newPassword: String): Boolean;
function userChangePassword(account: String; oldPassword: String; newPassword: String): Boolean;
function userChangeInfo(id: Integer; nickname: String; email: String; comment: String): Boolean;
function userUploadHead(id: Integer; head: String): Boolean;
function userGetInfo(id: Integer): User;

// theme
function themeAddStar(id: Integer; user: Integer): Boolean;
function themeRemoveStar(id: Integer; user: Integer): Boolean;
function themeUpload(author: Integer; name: String; description: String; themeFile: String): Integer;
function themeChangeFile(id: Integer; author: Integer; themeFile: String): Boolean;
function themeRemove(id: Integer; author: Integer): Boolean;
function themeChangeInfo(id: Integer; author: Integer; name: String; description: String): Boolean;
function themeGetInfo(id: Integer; user: Integer): Theme;
function themeGetDownloadUrl(id: Integer): String;
function themeGetList(page: Integer; pageSize: Integer; sort: String): TThemeList;
function themeGetListByUser(page: Integer; pageSize: Integer; author: Integer; sort: String): TThemeList;

// comment
function commentAdd(id: Integer; author: Integer; comment: String): Boolean;
function commentRemove(id: Integer; author: Integer): Boolean;
function commentGetList(id: Integer): TCommentList;

// UUID
procedure uuidAdd(year, month,day, hour, minute: Word; uuid: string);

// feedback
function feedbackAdd(appVer: Integer; userId: Integer; deviceId: string; nickname: string; email: string; content: string; img1: string; img2: string; img3: string): Boolean;

// system
function xposedInstalled(): Boolean;

// hotfix
function checkAndDownloadVersion(AVer: string): Boolean;
function loadVersion(AVer: string): TWechatVersion;

implementation

function userRegister(account: string; password: string; nickname: string;
  email: string): Integer;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := 0;
  param := TParamMap.Create;
  param.Add('account', account);
  param.Add('password', md5EncryptString(password));
  param.Add('nickname', nickname);
  param.Add('email', email);
  ret := HttpPost(BASEURL + 'user_register.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := json.Integers['userId'];
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function userLogin(account: string; password: string): User;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := nil;
  param := TParamMap.Create;
  param.Add('account', account);
  param.Add('password', md5EncryptString(password));
  ret := HttpPost(BASEURL + 'user_login.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := User.fromJson(json.Objects['userInfo']);
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function userValidateEmail(account: string; email: string): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('account', account);
  param.Add('email', email);
  ret := HttpPost(BASEURL + 'user_validate_email.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function userForgetPassword(account: String; email: String; newPassword: String
  ): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('account', account);
  param.Add('email', email);
  param.Add('newPassword', md5EncryptString(newPassword));
  ret := HttpPost(BASEURL + 'user_forget_password.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function userChangePassword(account: String; oldPassword: String;
  newPassword: String): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('account', account);
  param.Add('oldPassword', md5EncryptString(oldPassword));
  param.Add('newPassword', md5EncryptString(newPassword));
  ret := HttpPost(BASEURL + 'user_change_password.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function userChangeInfo(id: Integer; nickname: String; email: String;
  comment: String): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('nickname', nickname);
  param.Add('email', email);
  param.Add('comment', comment);
  ret := HttpPost(BASEURL + 'user_change_info.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function userUploadHead(id: Integer; head: String): Boolean;
var
  param: TParamMap;
  files: TParamMap;
  ret: String;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  files := TParamMap.Create;
  files.Add('head', head);
  ret := HttpPostFile(BASEURL + 'user_upload_head.php', param, files);
  files.Free;
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function userGetInfo(id: Integer): User;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := nil;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  ret := HttpPost(BASEURL + 'user_get_info.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := User.fromJson(json.Objects['userInfo']);
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeAddStar(id: Integer; user: Integer): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('user', IntToStr(user));
  ret := HttpPost(BASEURL + 'theme_add_star.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeRemoveStar(id: Integer; user: Integer): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('user', IntToStr(user));
  ret := HttpPost(BASEURL + 'theme_remove_star.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeUpload(author: Integer; name: String; description: String;
  themeFile: String): Integer;
var
  param: TParamMap;
  files: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := 0;
  param := TParamMap.Create;
  param.Add('author', IntToStr(author));
  param.Add('name', name);
  param.Add('description', description);
  files := TParamMap.Create;
  files.Add('themeFile', themeFile);
  ret := HttpPostFile(BASEURL + 'theme_upload.php', param, files);
  files.Free;
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := json.Integers['themeId'];
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeChangeFile(id: Integer; author: Integer; themeFile: String
  ): Boolean;
var
  param: TParamMap;
  files: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('author', IntToStr(author));
  files := TParamMap.Create;
  files.Add('themeFile', themeFile);
  ret := HttpPostFile(BASEURL + 'theme_change_file.php', param, files);
  files.Free;
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeRemove(id: Integer; author: Integer): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('author', IntToStr(author));
  ret := HttpPost(BASEURL + 'theme_remove.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeChangeInfo(id: Integer; author: Integer; name: String;
  description: String): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('author', IntToStr(author));
  param.Add('name', name);
  param.Add('description', description);
  ret := HttpPost(BASEURL + 'theme_change_info.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeGetInfo(id: Integer; user: Integer): Theme;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := nil;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('user', IntToStr(user));
  ret := HttpPost(BASEURL + 'theme_get_info.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := Theme.fromJson(json.Objects['themeInfo']);
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeGetDownloadUrl(id: Integer): String;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := '';
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  ret := HttpPost(BASEURL + 'theme_get_download_url.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := json.Strings['url'];
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeGetList(page: Integer; pageSize: Integer; sort: String
  ): TThemeList;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := nil;
  param := TParamMap.Create;
  param.Add('page', IntToStr(page));
  param.Add('pageSize', IntToStr(pageSize));
  param.Add('sort', sort);
  ret := HttpPost(BASEURL + 'theme_get_list.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := JsonToThemeList(json.Arrays['list']);
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function themeGetListByUser(page: Integer; pageSize: Integer; author: Integer;
  sort: String): TThemeList;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := nil;
  param := TParamMap.Create;
  param.Add('page', IntToStr(page));
  param.Add('pageSize', IntToStr(pageSize));
  param.Add('author', IntToStr(author));
  param.Add('sort', sort);
  ret := HttpPost(BASEURL + 'theme_get_list_by_user.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := JsonToThemeList(json.Arrays['list']);
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function commentAdd(id: Integer; author: Integer; comment: String): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('author', IntToStr(author));
  param.Add('comment', comment);
  ret := HttpPost(BASEURL + 'comment_add.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function commentRemove(id: Integer; author: Integer): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  param.Add('author', IntToStr(author));
  ret := HttpPost(BASEURL + 'comment_remove.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

function commentGetList(id: Integer): TCommentList;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
begin
  Result := nil;
  param := TParamMap.Create;
  param.Add('id', IntToStr(id));
  ret := HttpPost(BASEURL + 'comment_get_list.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      if (json.Integers['errcode'] = 0) then Result := JsonToCommentList(json.Arrays['list']);
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
end;

procedure uuidAdd(year, month, day, hour, minute: Word; uuid: string);
var
  param: TParamMap;
  ret: string = '';
begin
  param := TParamMap.Create;
  param.Add('year', IntToStr(year));
  param.Add('month', IntToStr(month));
  param.Add('day', IntToStr(day));
  param.Add('hour', IntToStr(hour));
  param.Add('minute', IntToStr(minute));
  param.Add('uuid', uuid);
  try
    ret := HttpPost(BASEURL + 'uid_add.php', param);
  except
  end;
  param.Free;
  LOGE(PChar(Format('Record UUID: %s', [ret])));
end;

procedure uploadFeedbackImage(fid: Integer; uid: Integer; imgid: Integer; f: string);
var
  param: TParamMap;
  fileParam: TParamMap;
  ret: string;
begin
  param := TParamMap.Create;
  param.Add('fid', IntToStr(fid));
  param.Add('uid', IntToStr(uid));
  param.Add('imgid', IntToStr(imgid));
  fileParam := TParamMap.Create;
  fileParam.Add('file', f);
  ret := HttpPostFile(BASEURL + 'feedback_upload_img.php', param, fileParam);
  fileParam.Free;
  param.Free;
  LOGE(PChar(Format('Upload feedback image => %s', [ret])));
end;

function feedbackAdd(appVer: Integer; userId: Integer; deviceId: string;
  nickname: string; email: string; content: string; img1: string; img2: string;
  img3: string): Boolean;
var
  param: TParamMap;
  ret: string;
  json: TJSONObject;
  parser: TJSONParser;
  fid: Integer = -1;
begin
  Result := False;
  param := TParamMap.Create;
  param.Add('appver', IntToStr(appVer));
  param.Add('userid', IntToStr(userId));
  param.Add('deviceid', deviceId);
  param.Add('nickname', nickname);
  param.Add('email', email);
  param.Add('content', content);
  ret := HttpPost(BASEURL + 'feedback_add.php', param);
  param.Free;
  if (ret.Trim <> '') then begin
    try
      parser := TJSONParser.Create(ret, [joUTF8]);
      json := TJSONObject(parser.Parse);
      fid:= json.Integers['result'];
      if (fid <> -1) then Result := True;
    finally
      if (json <> nil) then json.Free;
      if (parser <> nil) then parser.Free;
    end;
  end;
  // upload
  if (Result) then begin
    if (img1.Trim <> '') then uploadFeedbackImage(fid, userId, 1, img1);
    if (img2.Trim <> '') then uploadFeedbackImage(fid, userId, 2, img2);
    if (img3.Trim <> '') then uploadFeedbackImage(fid, userId, 3, img3);
  end;
end;

function xposedInstalled: Boolean;
const
  LIB1 = '/system/lib/libxposed_art.so';
  LIB2 = '/system/lib64/libxposed_art.so';
  PROP = '/system/xposed.prop';
var
  libExists: Boolean = False;
  propExists: Boolean = False;
begin
  if (FileExists(LIB1)) then libExists:= True;
  if (FileExists(LIB2)) then libExists:= True;
  if (FileExists(PROP)) then propExists:= True;
  Exit(libExists and propExists);
end;

function checkAndDownloadVersion(AVer: string): Boolean;
var
  APath: string;
  ret: string;
begin
  APath:= '/sdcard/.wechat_tophighlight/1/';
  if (not DirectoryExists(APath)) then ForceDirectories(APath);
  APath += AVer + '.ini';
  if (FileExists(APath)) then Exit(True);
  Result := False;
  try
    ret := HttpGet(BASEURL + 'ver/' + AVer + '.ini', nil);
  except
  end;
  if (ret.Trim <> '') then begin
    with TStringList.Create do begin
      Text:= ret;
      SaveToFile(APath);
      Result := True;
      Free;
    end;
  end;
end;

function loadVersion(AVer: string): TWechatVersion;
var
  APath: string;
begin
  Result := nil;
  try
    APath:= '/sdcard/.wechat_tophighlight/' + AVer + '.ini';
    if (FileExists(APath)) then Result := TWechatVersion.fromINI(APath);
  except
    on e: Exception do begin
      LOGE(PChar(Format('loadVersion => %s', [e.Message])));
    end;
  end;
end;

end.

