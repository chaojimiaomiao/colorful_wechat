unit wth_classes;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, JNI2, fpjson, strutils, fgl, IniFiles, FileUtil, BaseUnix, android;

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

  KEY_THEME_ID = 'theme_id';
  KEY_THEME_NAME = 'theme_name';
  KEY_THEME_TYPE = 'theme_type';
  KEY_THEME_NORMAL_COLOR = 'theme_normal_color';
  KEY_THEME_NORMAL_PRESS_COLOR = 'theme_normal_press_color';
  KEY_STATUSBAR_PATH = 'theme_statusbar_path';
  KEY_BOTTOMBAR_PATH = 'theme_bottombar_path';
  KEY_LIST_PATH = 'theme_list_path';

  SEC_WECHAT = 'wechat';
  KEY_LISTVIEW_ACT = 'listview_act';
  KEY_LISTVIEW_BACK_METHOD = 'listview_back_method';
  KEY_LISTVIEW_BACK_FIELD = 'listview_back_field';
  KEY_CONVERSATION_ADAPTER = 'conversation_adapter';
  KEY_USER_INFO_METHOD = 'user_info_method';
  KEY_TOP_INFO_METHOD = 'top_info_method';
  KEY_TOP_INFO_FIELD = 'top_info_field';

  KEY_MMFRAGMENT_ACTIVITY = 'mmfragment_activity';
  KEY_CHATUI_ACTIVITY = 'chatui_activity';
  KEY_GET_APPCOMPACT = 'get_appcompact';
  KEY_GET_ACTIONBAR = 'get_actionbar';
  KEY_DIVIDER_ID = 'divider_id';
  KEY_ACTIONBAR_VIEW_ID = 'actionbar_view_id';
  KEY_CUSTOMIZE_ACTIONBAR = 'customize_actionbar';
  KEY_ACTIONBAR_CONTAINER = 'actionbar_container';

  KEY_BOTTOM_TABVIEW = 'bottom_tabview';
  KEY_BOTTOM_METHOD = 'bottom_method';
  KEY_BOTTOM_FIELD = 'bottom_field';

  KEY_TOOL_CLASS = 'tool_class';
  KEY_TOOL_METHOD = 'tool_method';
  KEY_TOOL_FIELD = 'tool_field';

  KEY_POPUPWINDOW_CLASS = 'popupwindow_class';
  KEY_POPUP_FIELD = 'popup_field';
  KEY_POPUP_MENU_FIELD = 'popup_menu_field';
  KEY_POPUP_MENU_ITEM_CLASS = 'popup_menu_item_class';
  KEY_POPUP_MENU_ITEM_CONTAINER = 'popup_menu_item_container';
  KEY_POPUP_MENU_ITEM_ID = 'popup_menu_item_id';
  KEY_POPUPWINDOW_ADAPTER = 'popupwindow_adapter';

  KEY_TOP_MAC_ACTIVITY = 'top_mac_activity';
  KEY_TOP_READER_ACTIVITY = 'top_reader_activity';
  KEY_TOP_MAC_METHOD = 'top_mac_method';
  KEY_TOP_MAC_FIELD = 'top_mac_field';
  KEY_TOP_READER_METHOD = 'top_reader_method';
  KEY_TOP_READER_FIELD = 'top_reader_field';
  KEY_TOP_READER_VIEW_ID = 'top_reader_view_id';

  KEY_SETTING_ACTIVITY = 'setting_activity';
  KEY_SETTING_PREFERENCE = 'setting_preference';
  KEY_SETTING_LIST_FIELD = 'setting_list_field';
  KEY_SETTING_ADD_METHOD = 'setting_add_method';

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
    Fstared: Integer;
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
    property stared: Integer read Fstared write Fstared;
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
    FbottomBarPath: string;
    FdarkerStatusBar: Boolean;
    FdarkStatusBarText: Boolean;
    FdividerColor: Int64;
    FlistPath: string;
    FmacColor: Int64;
    FmacPressColor: Int64;
    FnormalColor: Int64;
    FnormalPressColor: Int64;
    FreaderColor: Int64;
    FreaderPressColor: Int64;
    FshowDivider: Boolean;
    FstatusBarColor: Int64;
    FstatusBarPath: string;
    FthemeId: Integer;
    FthemeName: string;
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
    F_type: Integer;
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

    property themeId: Integer read FthemeId write FthemeId;
    property themeName: string read FthemeName write FthemeName;
    property _type: Integer read F_type write F_type;
    property normalColor: Int64 read FnormalColor write FnormalColor;
    property normalPressColor: Int64 read FnormalPressColor write FnormalPressColor;
    property statusBarPath: string read FstatusBarPath write FstatusBarPath;
    property bottomBarPath: string read FbottomBarPath write FbottomBarPath;
    property listPath: string read FlistPath write FlistPath;
  end;

  TParamMap = specialize TFPGMap<string, string>;
  TThemeList = specialize TFPGList<Theme>;
  TCommentList = specialize TFPGList<ThemeComment>;

  { TWechatVersion }

  TWechatVersion = class
  private
    FactionBarContainer: string;
    FactionBarViewId: Int64;
    FbottomField: string;
    FbottomMethod: string;
    FbottomTabView: string;
    FchatUIActivity: string;
    FconversationAdapter: string;
    FcustomizeActionBar: string;
    FdividerId: Int64;
    FgetActionBar: string;
    FgetAppCompact: string;
    FlistviewAct: String;
    FlistviewBackField: string;
    FlistviewBackMethod: string;
    FmmFragmentActivity: string;
    FpopupField: string;
    FpopupMenuField: string;
    FpopupMenuItemClass: string;
    FpopupMenuItemContainer: string;
    FpopupMenuItemId: Int64;
    FpopupWindowAdapter: string;
    FpopupWindowClass: string;
    FsettingActivity: string;
    FsettingAddMethod: string;
    FsettingListField: string;
    FsettingPreference: string;
    FtoolClass: string;
    FtoolField: string;
    FtoolMethod: string;
    FtopInfoField: string;
    FtopInfoMethod: string;
    FtopMacActivity: string;
    FtopMacField: string;
    FtopMacMethod: string;
    FtopReaderActivity: string;
    FtopReaderField: string;
    FtopReaderMethod: string;
    FtopReaderViewId: Int64;
    FuserInfoMethod: string;
  public
    class function fromINI(APath: string): TWechatVersion;
    function toJObject(env: PJNIEnv): jobject;
  published

    property listviewAct: String read FlistviewAct write FlistviewAct;
    property listviewBackMethod: string read FlistviewBackMethod write FlistviewBackMethod;
    property listviewBackField: string read FlistviewBackField write FlistviewBackField;
    property conversationAdapter: string read FconversationAdapter write FconversationAdapter;
    property userInfoMethod: string read FuserInfoMethod write FuserInfoMethod;
    property topInfoMethod: string read FtopInfoMethod write FtopInfoMethod;
    property topInfoField: string read FtopInfoField write FtopInfoField;

    property mmFragmentActivity: string read FmmFragmentActivity write FmmFragmentActivity;
    property chatUIActivity: string read FchatUIActivity write FchatUIActivity;
    property getAppCompact: string read FgetAppCompact write FgetAppCompact;
    property getActionBar: string read FgetActionBar write FgetActionBar;
    property dividerId: Int64 read FdividerId write FdividerId;
    property actionBarViewId: Int64 read FactionBarViewId write FactionBarViewId;
    property customizeActionBar: string read FcustomizeActionBar write FcustomizeActionBar;
    property actionBarContainer: string read FactionBarContainer write FactionBarContainer;

    property bottomTabView: string read FbottomTabView write FbottomTabView;
    property bottomMethod: string read FbottomMethod write FbottomMethod;
    property bottomField: string read FbottomField write FbottomField;

    property toolClass: string read FtoolClass write FtoolClass;
    property toolMethod: string read FtoolMethod write FtoolMethod;
    property toolField: string read FtoolField write FtoolField;

    property popupWindowClass: string read FpopupWindowClass write FpopupWindowClass;
    property popupField: string read FpopupField write FpopupField;
    property popupMenuField: string read FpopupMenuField write FpopupMenuField;
    property popupMenuItemClass: string read FpopupMenuItemClass write FpopupMenuItemClass;
    property popupMenuItemContainer: string read FpopupMenuItemContainer write FpopupMenuItemContainer;
    property popupMenuItemId: Int64 read FpopupMenuItemId write FpopupMenuItemId;
    property popupWindowAdapter: string read FpopupWindowAdapter write FpopupWindowAdapter;

    property topMacActivity: string read FtopMacActivity write FtopMacActivity;
    property topReaderActivity: string read FtopReaderActivity write FtopReaderActivity;
    property topMacMethod: string read FtopMacMethod write FtopMacMethod;
    property topMacField: string read FtopMacField write FtopMacField;
    property topReaderMethod: string read FtopReaderMethod write FtopReaderMethod;
    property topReaderField: string read FtopReaderField write FtopReaderField;
    property topReaderViewId: Int64 read FtopReaderViewId write FtopReaderViewId;

    property settingActivity: string read FsettingActivity write FsettingActivity;
    property settingPreference: string read FsettingPreference write FsettingPreference;
    property settingListField: string read FsettingListField write FsettingListField;
    property settingAddMethod: string read FsettingAddMethod write FsettingAddMethod;

  end;

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
  objList:= env^^.NewObject(env, clsList, clsListMethod);
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

{ TWechatVersion }

class function TWechatVersion.fromINI(APath: string): TWechatVersion;
var
  sl: TStringList;
begin
  Result := nil;
  // load wechat version from ini
  if (not FileExists(APath)) then Exit;
  with TIniFile.Create(APath) do begin
    sl := TStringList.Create;
    ReadSectionValues(SEC_WECHAT, sl);
    Result := TWechatVersion.Create;
    with Result do begin
      listviewAct:= sl.Values[KEY_LISTVIEW_ACT];
      listviewBackMethod:= sl.Values[KEY_LISTVIEW_BACK_METHOD];
      listviewBackField:= sl.Values[KEY_LISTVIEW_BACK_FIELD];
      conversationAdapter:= sl.Values[KEY_CONVERSATION_ADAPTER];
      userInfoMethod:= sl.Values[KEY_USER_INFO_METHOD];
      topInfoMethod:= sl.Values[KEY_TOP_INFO_METHOD];
      topInfoField:= sl.Values[KEY_TOP_INFO_FIELD];

      mmFragmentActivity:= sl.Values[KEY_MMFRAGMENT_ACTIVITY];
      chatUIActivity:= sl.Values[KEY_CHATUI_ACTIVITY];
      getAppCompact:= sl.Values[KEY_GET_APPCOMPACT];
      getActionBar:= sl.Values[KEY_GET_ACTIONBAR];
      dividerId:= StrToInt64(sl.Values[KEY_DIVIDER_ID]);
      actionBarViewId:= StrToInt64(sl.Values[KEY_ACTIONBAR_VIEW_ID]);
      customizeActionBar:= sl.Values[KEY_CUSTOMIZE_ACTIONBAR];
      actionBarContainer:= sl.Values[KEY_ACTIONBAR_CONTAINER];

      bottomTabView:= sl.Values[KEY_BOTTOM_TABVIEW];
      bottomMethod:= sl.Values[KEY_BOTTOM_METHOD];
      bottomField:= sl.Values[KEY_BOTTOM_FIELD];

      toolClass := sl.Values[KEY_TOOL_CLASS];
      toolMethod:= sl.Values[KEY_TOOL_METHOD];
      toolField:= sl.Values[KEY_TOOL_FIELD];

      popupWindowClass:= sl.Values[KEY_POPUPWINDOW_CLASS];
      popupField:= sl.Values[KEY_POPUP_FIELD];
      popupMenuField:= sl.Values[KEY_POPUP_MENU_FIELD];
      popupMenuItemClass:= sl.Values[KEY_POPUP_MENU_ITEM_CLASS];
      popupMenuItemContainer:= sl.Values[KEY_POPUP_MENU_ITEM_CONTAINER];
      popupMenuItemId:= StrToInt64(sl.Values[KEY_POPUP_MENU_ITEM_ID]);
      popupWindowAdapter:= sl.Values[KEY_POPUPWINDOW_ADAPTER];

      topMacActivity:= sl.Values[KEY_TOP_MAC_ACTIVITY];
      topReaderActivity:= sl.Values[KEY_TOP_READER_ACTIVITY];
      topMacMethod:= sl.Values[KEY_TOP_MAC_METHOD];
      topMacField:= sl.Values[KEY_TOP_MAC_FIELD];
      topReaderMethod:= sl.Values[KEY_TOP_READER_METHOD];
      topReaderField:= sl.Values[KEY_TOP_READER_FIELD];
      topReaderViewId:= StrToInt64(sl.Values[KEY_TOP_READER_VIEW_ID]);

      settingActivity:= sl.Values[KEY_SETTING_ACTIVITY];
      settingPreference:= sl.Values[KEY_SETTING_PREFERENCE];
      settingListField:= sl.Values[KEY_SETTING_LIST_FIELD];
      settingAddMethod:= sl.Values[KEY_SETTING_ADD_METHOD];
    end;
    sl.Free;
    Free;
  end;
end;

function TWechatVersion.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  m: jmethodID;
begin
  LOGE('mapTo start');
  Result := nil;
  // map wechat version to jvm
  if (env = nil) then Exit;
  try
    cls := env^^.FindClass(env, 'com/rarnu/tophighlight/xposed/Versions');
    m := env^^.GetMethodID(env, cls, '<init>', '()V');
    Result := env^^.NewObject(env, cls, m);
    m := env^^.GetMethodID(env, cls, 'setListviewAct', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [listviewAct]));
    m := env^^.GetMethodID(env, cls, 'setListviewBackMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [listviewBackMethod]));
    m := env^^.GetMethodID(env, cls, 'setListviewBackField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [listviewBackField]));
    m := env^^.GetMethodID(env, cls, 'setConversationAdapter', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [conversationAdapter]));
    m := env^^.GetMethodID(env, cls, 'setUserInfoMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [userInfoMethod]));
    m := env^^.GetMethodID(env, cls, 'setTopInfoMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topInfoMethod]));
    m := env^^.GetMethodID(env, cls, 'setTopInfoField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topInfoField]));
    m := env^^.GetMethodID(env, cls, 'setMmFragmentActivity', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [mmFragmentActivity]));
    m := env^^.GetMethodID(env, cls, 'setChatUIActivity', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [chatUIActivity]));
    m := env^^.GetMethodID(env, cls, 'setGetAppCompact', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [getAppCompact]));
    m := env^^.GetMethodID(env, cls, 'setGetActionBar', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [getActionBar]));
    m := env^^.GetMethodID(env, cls, 'setDividerId', '(I)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [dividerId]));
    m := env^^.GetMethodID(env, cls, 'setActionBarViewId', '(I)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [actionBarViewId]));
    m := env^^.GetMethodID(env, cls, 'setCustomizeActionBar', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [customizeActionBar]));
    m := env^^.GetMethodID(env, cls, 'setActionBarContainer', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [actionBarContainer]));
    m := env^^.GetMethodID(env, cls, 'setBottomTabView', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [bottomTabView]));
    m := env^^.GetMethodID(env, cls, 'setBottomMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [bottomMethod]));
    m := env^^.GetMethodID(env, cls, 'setBottomField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [bottomField]));
    m := env^^.GetMethodID(env, cls, 'setToolClass', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [toolClass]));
    m := env^^.GetMethodID(env, cls, 'setToolMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [toolMethod]));
    m := env^^.GetMethodID(env, cls, 'setToolField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [toolField]));
    m := env^^.GetMethodID(env, cls, 'setPopupWindowClass', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [popupWindowClass]));
    m := env^^.GetMethodID(env, cls, 'setPopupField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [popupField]));
    m := env^^.GetMethodID(env, cls, 'setPopupMenuField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [popupMenuField]));
    m := env^^.GetMethodID(env, cls, 'setPopupMenuItemClass', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [popupMenuItemClass]));
    m := env^^.GetMethodID(env, cls, 'setPopupMenuItemContainer', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [popupMenuItemContainer]));
    m := env^^.GetMethodID(env, cls, 'setPopupMenuItemId', '(I)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [popupMenuItemId]));
    m := env^^.GetMethodID(env, cls, 'setPopupWindowAdapter', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [popupWindowAdapter]));
    m := env^^.GetMethodID(env, cls, 'setTopMacActivity', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topMacActivity]));
    m := env^^.GetMethodID(env, cls, 'setTopReaderActivity', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topReaderActivity]));
    m := env^^.GetMethodID(env, cls, 'setTopMacMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topMacMethod]));
    m := env^^.GetMethodID(env, cls, 'setTopMacField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topMacField]));
    m := env^^.GetMethodID(env, cls, 'setTopReaderMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topReaderMethod]));
    m := env^^.GetMethodID(env, cls, 'setTopReaderField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topReaderField]));
    m := env^^.GetMethodID(env, cls, 'setTopReaderViewId', '(I)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [topReaderViewId]));
    m := env^^.GetMethodID(env, cls, 'setSettingActivity', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [settingActivity]));
    m := env^^.GetMethodID(env, cls, 'setSettingPreference', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [settingPreference]));
    m := env^^.GetMethodID(env, cls, 'setSettingListField', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [settingListField]));
    m := env^^.GetMethodID(env, cls, 'setSettingAddMethod', '(Ljava/lang/String;)V');
    env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [settingAddMethod]));

  except
    on E: Exception do begin
      LOGE(PChar(Format('WechatVersion mapTo => %s', [E.Message])));
    end;
  end;
end;

{ ThemeIni }

class function ThemeIni.fromJObject(env: PJNIEnv; obj: jobject): ThemeIni;
var
  cls: jclass;
  m: jmethodID;
begin
  // finished
  Result := nil;
  if (obj <> nil) then begin
    Result := ThemeIni.Create;
    cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/ThemeINI');
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

    m := env^^.GetMethodID(env, cls, 'getThemeId', '()I');
    Result.themeId:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getThemeName', '()Ljava/lang/String;');
    Result.themeName:= TJNIEnv.JStringToString(env, env^^.CallObjectMethod(env, obj, m));
    m := env^^.GetMethodID(env, cls, 'getType', '()I');
    Result._type:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getNormalColor', '()I');
    Result.normalColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getNormalPressColor', '()I');
    Result.normalPressColor:= env^^.CallIntMethod(env, obj, m);
    m := env^^.GetMethodID(env, cls, 'getStatusBarPath', '()Ljava/lang/String;');
    Result.statusBarPath:= TJNIEnv.JStringToString(env, env^^.CallObjectMethod(env, obj, m));
    m := env^^.GetMethodID(env, cls, 'getBottomBarPath', '()Ljava/lang/String;');
    Result.bottomBarPath:= TJNIEnv.JStringToString(env, env^^.CallObjectMethod(env, obj, m));
    m := env^^.GetMethodID(env, cls, 'getListPath', '()Ljava/lang/String;');
    Result.listPath:= TJNIEnv.JStringToString(env, env^^.CallObjectMethod(env, obj, m));
  end;
end;

function binaryImageToFile(ms: TMemoryStream; path: string): string;
var
  dir: string;
  fpath: string;
begin
  dir := '/sdcard/.wechat_tophighlight/';
  if (not DirectoryExists(dir)) then ForceDirectories(dir);
  fpath:= dir + path;
  if (not FileExists(fpath)) then ms.SaveToFile(fpath);
  Exit(fpath);
end;

class function ThemeIni.fromINI(path: string): ThemeIni;
var
  ms: TMemoryStream;
  len: Integer;
  p: string;
  fpath: string;
begin
  // finished
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

      Result.themeId:= ReadInteger(SEC_THEME, KEY_THEME_ID, 0);
      Result.themeName:= ReadString(SEC_THEME, KEY_THEME_NAME, '');
      Result._type:= ReadInteger(SEC_THEME, KEY_THEME_TYPE, 0);
      Result.normalColor:= ReadInt64(SEC_THEME, KEY_THEME_NORMAL_COLOR, $fff5f5f5);
      Result.normalPressColor:= ReadInt64(SEC_THEME, KEY_THEME_NORMAL_PRESS_COLOR, $ffd9d9d9);

      ms := TMemoryStream.Create;
      len := ReadBinaryStream(SEC_THEME, KEY_STATUSBAR_PATH, ms);
      if (len > 0) then begin
        p := Format('img_%d_%s.png', [Result.themeId, 'statusbar']);
        fpath := binaryImageToFile(ms, p);
        Result.statusBarPath:= fpath;
      end else Result.statusBarPath:= '';
      ms.Free;
      ms := TMemoryStream.Create;
      len := ReadBinaryStream(SEC_THEME, KEY_BOTTOMBAR_PATH, ms);
      if (len > 0) then begin
        p := Format('img_%d_%s.png', [Result.themeId, 'bottombar']);
        fpath:= binaryImageToFile(ms, p);
        Result.bottomBarPath:= fpath;
      end else Result.bottomBarPath:= '';
      ms.Free;
      ms := TMemoryStream.Create;
      len := ReadBinaryStream(SEC_THEME, KEY_LIST_PATH, ms);
      if (len > 0) then begin
        p := Format('img_%d_%s.png', [Result.themeId, 'list']);
        fpath:= binaryImageToFile(ms, p);
        Result.listPath:= fpath;
      end else Result.listPath:= '';
      ms.Free;
      Free;
    end;
  end else begin
    LOGE(PChar(Format('File %s not exists!', [path])));
  end;
end;

function CopyStream(ms: TMemoryStream; AId: Integer; AFix: string): string;
var
  dir: string;
  fpath: string;
begin
  dir := '/sdcard/.wechat_tophighlight/';
  if (not DirectoryExists(dir)) then ForceDirectories(dir);
  fpath:= dir + Format('img_%d_%s.png', [AId, AFix]);
  if (not FileExists(fpath)) then ms.SaveToFile(fpath);
  ms.Seek(0, soFromBeginning);
  Exit(fpath);
end;

function ThemeIni.toINI(path: string): Boolean;
var
  base: string;
  ms: TMemoryStream;
begin
  base := ExtractFilePath(path);
  if (not DirectoryExists(base)) then ForceDirectories(base);

  // finished
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

      WriteInteger(SEC_THEME, KEY_THEME_ID, themeId);
      WriteString(SEC_THEME, KEY_THEME_NAME, themeName);
      WriteInteger(SEC_THEME, KEY_THEME_TYPE, _type);
      WriteInt64(SEC_THEME, KEY_THEME_NORMAL_COLOR, normalColor);
      WriteInt64(SEC_THEME, KEY_THEME_NORMAL_PRESS_COLOR, normalPressColor);

      // image
      if (FileExists(statusBarPath)) then begin
        ms := TMemoryStream.Create;
        ms.LoadFromFile(statusBarPath);
        CopyStream(ms, themeId, 'statusbar');
        WriteBinaryStream(SEC_THEME, KEY_STATUSBAR_PATH, ms);
        ms.Free;
      end;

      if (FileExists(bottomBarPath)) then begin
        ms := TMemoryStream.Create;
        ms.LoadFromFile(bottomBarPath);
        CopyStream(ms, themeId, 'bottombar');
        WriteBinaryStream(SEC_THEME, KEY_BOTTOMBAR_PATH, ms);
        ms.Free;
      end;

      if (FileExists(listPath)) then begin
        ms := TMemoryStream.Create;
        ms.LoadFromFile(listPath);
        CopyStream(ms, themeId, 'list');
        WriteBinaryStream(SEC_THEME, KEY_LIST_PATH, ms);
        ms.Free;
      end;
      Free;
    end;
    Result := True;
  except
    on E: Exception do begin
      LOGE(PChar(Format('Save INI Failed => %s', [E.Message])));
    end;
  end;
  FpChmod(path, &755);
end;

function ThemeIni.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  clsMethod: jmethodID;
  m: jmethodID;
begin
  // finished
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/ThemeINI');
  clsMethod:= env^^.GetMethodID(env, cls, '<init>', '()V');
  Result := env^^.NewObject(env, cls, clsMethod);
  m := env^^.GetMethodID(env, cls, 'setThemeId', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FthemeId]));
  m := env^^.GetMethodID(env, cls, 'setThemeName', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FthemeName]));
  m := env^^.GetMethodID(env, cls, 'setType', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [F_type]));
  m := env^^.GetMethodID(env, cls, 'setNormalColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FnormalColor]));
  m := env^^.GetMethodID(env, cls, 'setNormalPressColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FnormalPressColor]));
  m := env^^.GetMethodID(env, cls, 'setStatusBarPath', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FstatusBarPath]));
  m := env^^.GetMethodID(env, cls, 'setBottomBarPath', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FbottomBarPath]));
  m := env^^.GetMethodID(env, cls, 'setListPath', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FlistPath]));
  m := env^^.GetMethodID(env, cls, 'setStatusBarColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FstatusBarColor]));
  m := env^^.GetMethodID(env, cls, 'setShowDivider', '(Z)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FshowDivider]));
  m := env^^.GetMethodID(env, cls, 'setDividerColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FdividerColor]));
  m := env^^.GetMethodID(env, cls, 'setDarkerStatusBar', '(Z)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FdarkerStatusBar]));
  m := env^^.GetMethodID(env, cls, 'setDarkStatusBarText', '(Z)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FdarkStatusBarText]));
  m := env^^.GetMethodID(env, cls, 'setMacColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FmacColor]));
  m := env^^.GetMethodID(env, cls, 'setMacPressColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FmacPressColor]));
  m := env^^.GetMethodID(env, cls, 'setReaderColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FreaderColor]));
  m := env^^.GetMethodID(env, cls, 'setReaderPressColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FreaderPressColor]));
  m := env^^.GetMethodID(env, cls, 'setBottomBarColor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FbottomBarColor]));
  m := env^^.GetMethodID(env, cls, 'setTopColors0', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors0]));
  m := env^^.GetMethodID(env, cls, 'setTopColors1', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors1]));
  m := env^^.GetMethodID(env, cls, 'setTopColors2', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors2]));
  m := env^^.GetMethodID(env, cls, 'setTopColors3', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors3]));
  m := env^^.GetMethodID(env, cls, 'setTopColors4', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors4]));
  m := env^^.GetMethodID(env, cls, 'setTopColors5', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors5]));
  m := env^^.GetMethodID(env, cls, 'setTopColors6', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors6]));
  m := env^^.GetMethodID(env, cls, 'setTopColors7', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors7]));
  m := env^^.GetMethodID(env, cls, 'setTopColors8', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors8]));
  m := env^^.GetMethodID(env, cls, 'setTopColors9', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopColors9]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors0', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors0]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors1', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors1]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors2', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors2]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors3', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors3]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors4', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors4]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors5', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors5]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors6', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors6]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors7', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors7]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors8', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors8]));
  m := env^^.GetMethodID(env, cls, 'setTopPressColors9', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [FtopPressColors9]));
end;

{ ThemeComment }

function ThemeComment.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  clsMethod: jmethodID;
  m: jmethodID;
begin
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/ThemeComment');
  clsMethod:= env^^.GetMethodID(env, cls, '<init>', '()V');
  Result := env^^.NewObject(env, cls, clsMethod);
  m := env^^.GetMethodID(env, cls, 'setId', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fid]));
  m := env^^.GetMethodID(env, cls, 'setAuthor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fauthor]));
  m := env^^.GetMethodID(env, cls, 'setNickname', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fnickname]));
  m := env^^.GetMethodID(env, cls, 'setPublishdate', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fpublishdate]));
  m := env^^.GetMethodID(env, cls, 'setComment', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fcomment]));
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
  m: jmethodID;
begin
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/Theme');
  clsMethod:= env^^.GetMethodID(env, cls, '<init>', '()V');
  Result := env^^.NewObject(env, cls, clsMethod);
  m := env^^.GetMethodID(env, cls, 'setId', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fid]));
  m := env^^.GetMethodID(env, cls, 'setName', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fname]));
  m := env^^.GetMethodID(env, cls, 'setAuthor', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fauthor]));
  m := env^^.GetMethodID(env, cls, 'setPublishdate', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fpublishdate]));
  m := env^^.GetMethodID(env, cls, 'setDescription', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fdescription]));
  m := env^^.GetMethodID(env, cls, 'setDownloadcount', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fdownloadcount]));
  m := env^^.GetMethodID(env, cls, 'setStarcount', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fstarcount]));
  m := env^^.GetMethodID(env, cls, 'setStared', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fstared]));
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
    Result.stared:= json.Integers['stared'];
  end;
end;

function Theme.ToString: ansistring;
begin
  Result := Format('Class => Theme'#13#10'id => %d'#13#10'name => %s'#13#10'author => %d'#13#10'publishdate => %s'#13#10'description => %s'#13#10'downloadcount = %d'#13#10'starcount => %d'#13#10'stared => %d'#13#10, [Fid, Fname, Fauthor, Fpublishdate, Fdescription, Fdownloadcount, Fstarcount, Fstared]);
end;

{ User }

function User.toJObject(env: PJNIEnv): jobject;
var
  cls: jclass;
  clsMethod: jmethodID;
  m: jmethodID;
begin
  cls := env^^.FindClass(env, 'com/rarnu/tophighlight/api/User');
  clsMethod := env^^.GetMethodID(env, cls, '<init>', '()V');
  Result := env^^.NewObject(env, cls, clsMethod);
  m := env^^.GetMethodID(env, cls, 'setId', '(I)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fid]));
  m := env^^.GetMethodID(env, cls, 'setAccount', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Faccount]));
  m := env^^.GetMethodID(env, cls, 'setNickname', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fnickname]));
  m := env^^.GetMethodID(env, cls, 'setEmail', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Femail]));
  m := env^^.GetMethodID(env, cls, 'setHead', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fhead]));
  m := env^^.GetMethodID(env, cls, 'setComment', '(Ljava/lang/String;)V');
  env^^.CallVoidMethodA(env, Result, m, TJNIEnv.ArgsToJValues(env, [Fcomment]));
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

