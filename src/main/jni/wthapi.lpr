{.$DEFINE DEBUG}

{$IFDEF DEBUG} program {$ELSE} library {$ENDIF} wthapi;

{$mode objfpc}{$H+}

uses
  Classes, sysutils, jni2, jni_utils, api_export, http_utils, test, wth_classes;

{$IFNDEF DEBUG}
exports
  Java_com_rarnu_tophighlight_api_WthApi_userRegister,
  Java_com_rarnu_tophighlight_api_WthApi_userLogin;
{$ENDIF}

begin
  {$IFDEF DEBUG}
  doTest();
  {$ENDIF}
end.

