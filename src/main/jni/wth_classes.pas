unit wth_classes;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, jni2, jni_utils, fpjson;

type

  IWthIntf = interface
  ['{E8DBF903-10B5-456D-B074-038DAE89C9C1}']
  function toJObject(env: PJNIEnv): jobject;
  end;

  { User }

  User = class(TInterfacedObject, IWthIntf)
  private
    Faccount: String;
    Fcomment: string;
    Femail: string;
    Fhead: string;
    Fid: Integer;
    Fnickname: string;
  public
    function toJObject(env: PJNIEnv): jobject;
    class function fromJson(json: TJSONObject): User;
    function ToString: ansistring; override;
  public
    property id: Integer read Fid write Fid;
    property account: String read Faccount write Faccount;
    property nickname: string read Fnickname write Fnickname;
    property email: string read Femail write Femail;
    property head: string read Fhead write Fhead;
    property comment: string read Fcomment write Fcomment;
  end;



implementation

{ User }

function User.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  clsMethod: jmethodID;
  jParam: Pjvalue;
begin
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/WthApi$User');
  clsMethod := env^^.GetMethodID(env, cls, '<init>', '(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V');
  jParam:= argsToJValues(env, [Fid, Faccount, Fnickname, Femail, Fhead, Fcomment]);
  Result := env^^.NewObjectA(env, cls, clsMethod, jParam);
end;

class function User.fromJson(json: TJSONObject): User;
begin
  Result := nil;
  if (json <> nil) then begin
    Result := User.Create;
    Result.id:= json.Integers['id'];
    Result.account:= json.Strings['account'];
    Result.nickname:= json.Strings['nickname'];
    Result.email:= json.Strings['email'];
    Result.head:= json.Strings['head'];
    Result.comment:= json.Strings['comment'];
  end;
end;

function User.ToString: ansistring;
begin
  Result := Format('Class => User'#13#10'id => %d'#13#10'account => %s'#13#10'nickname => %s'#13#10'email => %s'#13#10'head => %s'#13#10'comment => %s'#13#10, [Fid, Faccount, Fnickname, Femail, Fhead, Fcomment]);
end;

end.

