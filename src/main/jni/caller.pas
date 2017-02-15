unit caller;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, http_utils, encrypt_utils, fpjson, jsonparser, jsonscanner, wth_classes;

const
  BASEURL = 'http://rarnu.com/wth/';

function userRegister(account: string; password: string; nickname: string; email: string): Integer;
function userLogin(account: string; password: string): User;

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


end.

