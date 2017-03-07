unit wth_classes;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, JNI2, fpjson, strutils, fgl, IniFiles;

const
  SEC_THEME = 'theme';
  KEY_THEME_STATUSBAR_COLOR = 'theme_status_bar_color';
  KEY_THEME_SHOW_DIVIDER = 'theme_show_divider';
  KEY_THEME_DIVIDER_COLOR = 'theme_divider_color';
  KEY_THEME_DARKER_STATUSBAR = 'theme_darker_statusbar';
  KEY_THEME_DARK_STATUSBAR_TEXT = 'theme_dark_statusbar_text';
  KEY_THEME_MAC_COLOR = 'theme_mac_color';
  KEY_THEME_MAC_PRESS_COLOR = 'theme_mac_press_color';
  KEY_THEME_READER_COLOR = 'theme_reader_color';
  KEY_THEME_READER_PRESS_COLOR = 'theme_reader_press_color';
  KEY_THEME_BOTTOMBAR_COLOR = 'theme_bottombar_color';
  KEY_THEME_TOP_COLOR = 'theme_top_color_%d';
  KEY_THEME_TOP_PRESS_COLOR = 'theme_top_press_color_%d';

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

  { ThemeIni }

  ThemeIni = class
  private
    FbottomBarColor: Int64;
    FdarkerStatusBar: Boolean;
    FdarkStatusBarText: Boolean;
    FdividerColor: Int64;
    FmacColor: Int64;
    FmacPressColor: Int64;
    FreaderColor: Int64;
    FreaderPressColor: Int64;
    FshowDivider: Boolean;
    FstatusBarColor: Int64;
    FtopColors0: Int64;
    FtopColors1: Int64;
    FtopColors2: Int64;
    FtopColors3: Int64;
    FtopColors4: Int64;
    FtopColors5: Int64;
    FtopColors6: Int64;
    FtopColors7: Int64;
    FtopColors8: Int64;
    FtopColors9: Int64;
    FtopPressColors0: Int64;
    FtopPressColors1: Int64;
    FtopPressColors2: Int64;
    FtopPressColors3: Int64;
    FtopPressColors4: Int64;
    FtopPressColors5: Int64;
    FtopPressColors6: Int64;
    FtopPressColors7: Int64;
    FtopPressColors8: Int64;
    FtopPressColors9: Int64;
  public
    class function fromJObject(env: PJNIEnv; obj: jobject): ThemeIni;
    class function fromINI(path: string): ThemeIni;
    function toINI(path: string): Boolean;
    function toJObject(env: PJNIEnv): jobject;
  published
    property statusBarColor: Int64 read FstatusBarColor write FstatusBarColor;
    property showDivider: Boolean read FshowDivider write FshowDivider;
    property dividerColor: Int64 read FdividerColor write FdividerColor;
    property darkerStatusBar: Boolean read FdarkerStatusBar write FdarkerStatusBar;
    property darkStatusBarText: Boolean read FdarkStatusBarText write FdarkStatusBarText;
    property macColor: Int64 read FmacColor write FmacColor;
    property macPressColor: Int64 read FmacPressColor write FmacPressColor;
    property readerColor: Int64 read FreaderColor write FreaderColor;
    property readerPressColor: Int64 read FreaderPressColor write FreaderPressColor;
    property bottomBarColor: Int64 read FbottomBarColor write FbottomBarColor;
    property topColors0: Int64 read FtopColors0 write FtopColors0;
    property topColors1: Int64 read FtopColors1 write FtopColors1;
    property topColors2: Int64 read FtopColors2 write FtopColors2;
    property topColors3: Int64 read FtopColors3 write FtopColors3;
    property topColors4: Int64 read FtopColors4 write FtopColors4;
    property topColors5: Int64 read FtopColors5 write FtopColors5;
    property topColors6: Int64 read FtopColors6 write FtopColors6;
    property topColors7: Int64 read FtopColors7 write FtopColors7;
    property topColors8: Int64 read FtopColors8 write FtopColors8;
    property topColors9: Int64 read FtopColors9 write FtopColors9;
    property topPressColors0: Int64 read FtopPressColors0 write FtopPressColors0;
    property topPressColors1: Int64 read FtopPressColors1 write FtopPressColors1;
    property topPressColors2: Int64 read FtopPressColors2 write FtopPressColors2;
    property topPressColors3: Int64 read FtopPressColors3 write FtopPressColors3;
    property topPressColors4: Int64 read FtopPressColors4 write FtopPressColors4;
    property topPressColors5: Int64 read FtopPressColors5 write FtopPressColors5;
    property topPressColors6: Int64 read FtopPressColors6 write FtopPressColors6;
    property topPressColors7: Int64 read FtopPressColors7 write FtopPressColors7;
    property topPressColors8: Int64 read FtopPressColors8 write FtopPressColors8;
    property topPressColors9: Int64 read FtopPressColors9 write FtopPressColors9;
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
    env^^.CallBooleanMethodA(env, objList, clsListAdd, TJNIEnv.argsToJValues(env, [list[i].toJObject(env)]));
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
    env^^.CallBooleanMethodA(env, objList, clsListAdd, TJNIEnv.argsToJValues(env, [list[i].toJObject(env)]));
  end;
  Result := objList;
end;

{ ThemeIni }

class function ThemeIni.fromJObject(env: PJNIEnv; obj: jobject): ThemeIni;
var
  cls: jclass;
  m: jmethodID;
begin
  Result := nil;
  if (obj <> nil) then begin
    Result := ThemeIni.Create;
    cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/WthApi$ThemeINI');
    m := env^^.GetMethodID(env, cls, 'getStatusBarColor', '()I');
    Result.statusBarColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getShowDivider', '()Z');
    Result.showDivider:= env^^.CallBooleanMethod(env, obj, m) = JNI_TRUE;
    m := env^^.GetMethodID(env, cls, 'getDividerColor', '()I');
    Result.dividerColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getDarkerStatusBar', '()Z');
    Result.darkerStatusBar:= env^^.CallBooleanMethod(env, obj, m) = JNI_TRUE;
    m := env^^.GetMethodID(env, cls, 'getDarkStatusBarText', '()Z');
    Result.darkStatusBarText:= env^^.CallBooleanMethod(env, obj, m) = JNI_TRUE;
    m := env^^.GetMethodID(env, cls, 'getMacColor', '()I');
    Result.macColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getMacPressColor', '()I');
    Result.macPressColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getReaderColor', '()I');
    Result.readerColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getReaderPressColor', '()I');
    Result.readerPressColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getBottomBarColor', '()I');
    Result.bottomBarColor:= env^^.CallIntMethod(env, obj ,m);

    m := env^^.GetMethodID(env, cls, 'getTopColors0', '()I');
    Result.topColors0:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors1', '()I');
    Result.topColors1:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors2', '()I');
    Result.topColors2:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors3', '()I');
    Result.topColors3:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors4', '()I');
    Result.topColors4:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors5', '()I');
    Result.topColors5:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors6', '()I');
    Result.topColors6:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors7', '()I');
    Result.topColors7:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors8', '()I');
    Result.topColors8:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopColors9', '()I');
    Result.topColors9:= env^^.CallIntMethod(env, obj, m);

    m := env^^.GetMethodID(env, cls, 'getTopPressColors0', '()I');
    Result.topPressColors0:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors1', '()I');
    Result.topPressColors1:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors2', '()I');
    Result.topPressColors2:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors3', '()I');
    Result.topPressColors3:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors4', '()I');
    Result.topPressColors4:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors5', '()I');
    Result.topPressColors5:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors6', '()I');
    Result.topPressColors6:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors7', '()I');
    Result.topPressColors7:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors8', '()I');
    Result.topPressColors8:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getTopPressColors9', '()I');
    Result.topPressColors9:= env^^.CallIntMethod(env, obj, m);
  end;
end;

class function ThemeIni.fromINI(path: string): ThemeIni;
begin
  Result := nil;
  if (FileExists(path)) then begin
    Result := ThemeIni.Create;
    with TIniFile.Create(path) do begin
      Result.statusBarColor:= ReadInt64(SEC_THEME, KEY_THEME_STATUSBAR_COLOR, $ffffc7c8);
      Result.showDivider:= ReadBool(SEC_THEME, KEY_THEME_SHOW_DIVIDER, False);
      Result.dividerColor:= ReadInt64(SEC_THEME, KEY_THEME_DIVIDER_COLOR, $ffff8f8e);
      Result.darkerStatusBar:= ReadBool(SEC_THEME, KEY_THEME_DARKER_STATUSBAR, True);
      Result.darkStatusBarText:= ReadBool(SEC_THEME, KEY_THEME_DARK_STATUSBAR_TEXT, False);
      Result.macColor:= ReadInt64(SEC_THEME, KEY_THEME_MAC_COLOR, $fff5f5f5);
      Result.macPressColor:= ReadInt64(SEC_THEME, KEY_THEME_MAC_PRESS_COLOR, $ffd9d9d9);
      Result.readerColor:= ReadInt64(SEC_THEME, KEY_THEME_READER_COLOR, $fff5f5f5);
      Result.readerPressColor:= ReadInt64(SEC_THEME, KEY_THEME_READER_PRESS_COLOR, $ffd9d9d9);
      Result.bottomBarColor:= ReadInt64(SEC_THEME, KEY_THEME_BOTTOMBAR_COLOR, $ffffffff);
      Result.topColors0:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [0]), $ffffc7c8);
      Result.topPressColors0:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [0]), $ffff8f8e);
      Result.topColors1:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [1]), $fffeeac7);
      Result.topPressColors1:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [1]), $ffffd38f);
      Result.topColors2:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [2]), $ffffffc9);
      Result.topPressColors2:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [2]), $ffffff8d);
      Result.topColors3:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [3]), $ffc9fec6);
      Result.topPressColors3:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [3]), $ff8efe8e);
      Result.topColors4:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [4]), $ffc8fefe);
      Result.topPressColors4:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [4]), $ff8dffff);
      Result.topColors5:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [5]), $ffc7c8ff);
      Result.topPressColors5:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [5]), $ff8f8fff);
      Result.topColors6:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [6]), $ffedc7ff);
      Result.topPressColors6:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [6]), $ffdb8eff);
      Result.topColors7:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [7]), $ffeeeeee);
      Result.topPressColors7:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [7]), $ffd9d9d9);
      Result.topColors8:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [8]), $ffeeeeee);
      Result.topPressColors8:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [8]), $ffd9d9d9);
      Result.topColors9:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [9]), $ffeeeeee);
      Result.topPressColors9:= ReadInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [9]), $ffd9d9d9);
      Free;
    end;
  end;
end;

function ThemeIni.toINI(path: string): Boolean;
begin
  Result := False;
  try
    with TIniFile.Create(path) do begin
      WriteInt64(SEC_THEME, KEY_THEME_STATUSBAR_COLOR, statusBarColor);
      WriteBool(SEC_THEME, KEY_THEME_SHOW_DIVIDER, showDivider);
      WriteInt64(SEC_THEME, KEY_THEME_DIVIDER_COLOR, dividerColor);
      WriteBool(SEC_THEME, KEY_THEME_DARKER_STATUSBAR, darkerStatusBar);
      WriteBool(SEC_THEME, KEY_THEME_DARK_STATUSBAR_TEXT, darkStatusBarText);
      WriteInt64(SEC_THEME, KEY_THEME_MAC_COLOR, macColor);
      WriteInt64(SEC_THEME, KEY_THEME_MAC_PRESS_COLOR, macPressColor);
      WriteInt64(SEC_THEME, KEY_THEME_READER_COLOR, readerColor);
      WriteInt64(SEC_THEME, KEY_THEME_READER_PRESS_COLOR, readerPressColor);
      WriteInt64(SEC_THEME, KEY_THEME_BOTTOMBAR_COLOR, bottomBarColor);

      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [0]), topColors0);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [0]), topPressColors0);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [1]), topColors1);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [1]), topPressColors1);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [2]), topColors2);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [2]), topPressColors2);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [3]), topColors3);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [3]), topPressColors3);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [4]), topColors4);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [4]), topPressColors4);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [5]), topColors5);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [5]), topPressColors5);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [6]), topColors6);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [6]), topPressColors6);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [7]), topColors7);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [7]), topPressColors7);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [8]), topColors8);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [8]), topPressColors8);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_COLOR, [9]), topColors9);
      WriteInt64(SEC_THEME, Format(KEY_THEME_TOP_PRESS_COLOR, [9]), topPressColors9);

      Free;
    end;
    Result := True;
  except
  end;
end;

function ThemeIni.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  m: jmethodID;
  args: Pjvalue;
begin
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/WthApi$ThemeINI');
  m := env^^.GetMethodID(env, cls, '<init>', '(IZIZZIIIIIIIIIIIIIIIIIIIIIIIII)V');
  args := TJNIEnv.argsToJValues(env, [statusBarColor, showDivider, dividerColor, darkerStatusBar, darkStatusBarText,
    macColor, macPressColor, readerColor, readerPressColor, bottomBarColor,
    topColors0, topColors1, topColors2, topColors3, topColors4, topColors5, topColors6, topColors7, topColors8, topColors9,
    topPressColors0, topPressColors1, topPressColors2, topPressColors3, topPressColors4, topPressColors5, topPressColors6, topPressColors7, topPressColors8, topPressColors9]);
  Result := env^^.NewObjectA(env, cls, m, args);
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
  jParam:= TJNIEnv.argsToJValues(env, [Fid, Fauthor, Fnickname, Fpublishdate, Fcomment]);
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
  jParam:= TJNIEnv.argsToJValues(env, [Fid, Fname, Fauthor, Fpublishdate, Fdescription, Fdownloadcount, Fstarcount, Fstared]);
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
  jParam:= TJNIEnv.argsToJValues(env, [Fid, Faccount, Fnickname, Femail, Fhead, Fcomment]);
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

