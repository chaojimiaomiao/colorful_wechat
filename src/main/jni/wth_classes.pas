unit wth_classes;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, jni2, jni_utils, fpjson, strutils, fgl;

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

  { Theme }

  Theme = class(TInterfacedObject, IWthIntf)
  private
    Fauthor: Integer;
    Fdescription: string;
    Fdownloadcount: Integer;
    Fid: Integer;
    Fname: string;
    Fpublishdate: String;
    Fstarcount: Integer;
    Fstared: Boolean;
  public
    function toJObject(env: PJNIEnv): jobject;
    class function fromJson(json: TJSONObject): Theme;
    function ToString: ansistring; override;
  public
    property id: Integer read Fid write Fid;
    property name: string read Fname write Fname;
    property author: Integer read Fauthor write Fauthor;
    property publishdate: String read Fpublishdate write Fpublishdate;
    property description: string read Fdescription write Fdescription;
    property downloadcount: Integer read Fdownloadcount write Fdownloadcount;
    property starcount: Integer read Fstarcount write Fstarcount;
    property stared: Boolean read Fstared write Fstared;
  end;

  { ThemeComment }

  ThemeComment = class(TInterfacedObject, IWthIntf)
  private
    Fauthor: Integer;
    Fcomment: string;
    Fid: Integer;
    Fnickname: string;
    Fpublishdate: string;
  public
    function toJObject(env: PJNIEnv): jobject;
    class function fromJson(json: TJSONObject): ThemeComment;
    function ToString: ansistring; override;
  public
    property id: Integer read Fid write Fid;
    property author: Integer read Fauthor write Fauthor;
    property nickname: string read Fnickname write Fnickname;
    property publishdate: string read Fpublishdate write Fpublishdate;
    property comment: string read Fcomment write Fcomment;
  end;


  TParamMap = specialize TFPGMap<string, string>;
  TThemeList = specialize TFPGList<Theme>;
  TCommentList = specialize TFPGList<ThemeComment>;

function JsonToThemeList(jarr: TJSONArray): TThemeList;
function ThemeListToJobject(env: PJNIEnv; list: TThemeList): jobject;

function JsonToCommentList(jarr: TJSONArray): TCommentList;
function CommentListToJObject(env: PJNIEnv; list: TCommentList): jobject;

implementation

function JsonToThemeList(jarr: TJSONArray): TThemeList;
var
  i: Integer;
begin
  Result := nil;
  if (jarr <> nil) then begin
    Result := TThemeList.Create;
    for i := 0 to jarr.Count - 1 do Result.Add(Theme.fromJson(jarr.Objects[i]));
  end;
end;

function ThemeListToJobject(env: PJNIEnv; list: TThemeList): jobject;
var
  clsList: jclass;
  clsListMethod: jmethodID;
  clsListAdd: jmethodID;
  objList: jobject;
  i: Integer;
begin
  // theme list to jobject
  clsList:= env^^.FindClass(env, 'java/util/ArrayList');
  clsListMethod:= env^^.GetMethodID(env, clsList, '<init>', '()V');
  clsListAdd:= env^^.GetMethodID(env, clsList, 'add', '(Ljava/lang/Object;)Z');
  objList:= env^^.NewObjectA(env, clsList, clsListMethod, nil);
  if (list <> nil) then for i := 0 to list.Count - 1 do begin
    env^^.CallBooleanMethodA(env, objList, clsListAdd, argsToJValues(env, [list[i].toJObject(env)]));
  end;
  Result := objList;
end;

function JsonToCommentList(jarr: TJSONArray): TCommentList;
var
  i: Integer;
begin
  Result := nil;
  if (jarr <> nil) then begin
    Result := TCommentList.Create;
    for i := 0 to jarr.Count - 1 do Result.Add(ThemeComment.fromJson(jarr.Objects[i]));
  end;
end;

function CommentListToJObject(env: PJNIEnv; list: TCommentList): jobject;
var
  clsList: jclass;
  clsListMethod: jmethodID;
  clsListAdd: jmethodID;
  objList: jobject;
  i: Integer;
begin
  // comment list to jobject
  clsList:= env^^.FindClass(env, 'java/util/ArrayList');
  clsListMethod:= env^^.GetMethodID(env, clsList, '<init>', '()V');
  clsListAdd:= env^^.GetMethodID(env, clsList, 'add', '(Ljava/lang/Object;)Z');
  objList:= env^^.NewObjectA(env, clsList, clsListMethod, nil);
  if (list <> nil) then for i := 0 to list.Count - 1 do begin
    env^^.CallBooleanMethodA(env, objList, clsListAdd, argsToJValues(env, [list[i].toJObject(env)]));
  end;
  Result := objList;
end;

{ ThemeComment }

function ThemeComment.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  clsMethod: jmethodID;
  jParam: Pjvalue;
begin
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/WthApi$ThemeComment');
  clsMethod:= env^^.GetMethodID(env, cls, '<init>', '(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V');
  jParam:= argsToJValues(env, [Fid, Fauthor, Fnickname, Fpublishdate, Fcomment]);
  Result := env^^.NewObjectA(env, cls, clsMethod, jParam);
end;

class function ThemeComment.fromJson(json: TJSONObject): ThemeComment;
begin
  Result := nil;
  if (json <> nil) then begin
    Result := ThemeComment.Create;
    Result.id:= json.Integers['id'];
    Result.author:= json.Integers['author'];
    Result.nickname:= json.Strings['nickname'];
    Result.publishdate:= json.Strings['publishdate'];
    Result.comment:= json.Strings['comment'];
  end;
end;

function ThemeComment.ToString: ansistring;
begin
  Result:= Format('Class => ThemeComment'#13#10'id = %d'#13#10'author = %d'#13#10'nickname => %s'#13#10'publishdate => %s'#13#10'comment => %s'#13#10, [Fid, Fauthor, Fnickname, Fpublishdate, Fcomment]);
end;

{ Theme }

function Theme.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  clsMethod: jmethodID;
  jParam: Pjvalue;
begin
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/WthApi$Theme');
  clsMethod:= env^^.GetMethodID(env, cls, '<init>', '(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IIZ)V');
  jParam:= argsToJValues(env, [Fid, Fname, Fauthor, Fpublishdate, Fdescription, Fdownloadcount, Fstarcount, Fstared]);
  Result := env^^.NewObjectA(env, cls, clsMethod, jParam);
end;

class function Theme.fromJson(json: TJSONObject): Theme;
begin
  Result := nil;
  if (json <> nil) then begin
    Result := Theme.Create;
    Result.id:= json.Integers['id'];
    Result.name:= json.Strings['name'];
    Result.author:= json.Integers['author'];
    Result.publishdate:= json.Strings['publishdate'];
    Result.description:= json.Strings['description'];
    Result.downloadcount:= json.Integers['downloadcount'];
    Result.starcount:= json.Integers['starcount'];
    Result.stared:= json.Integers['stared'] <> 0;
  end;
end;

function Theme.ToString: ansistring;
begin
  Result := Format('Class => Theme'#13#10'id => %d'#13#10'name => %s'#13#10'author => %d'#13#10'publishdate => %s'#13#10'description => %s'#13#10'downloadcount = %d'#13#10'starcount => %d'#13#10'stared => %s'#13#10, [Fid, Fname, Fauthor, Fpublishdate, Fdescription, Fdownloadcount, Fstarcount, IfThen(Fstared, 'TRUE', 'FALSE')]);
end;

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

