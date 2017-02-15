unit test;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, caller, wth_classes;

procedure doTest();

implementation

procedure doTest;
var
  retRegister: Integer;
  retLogin: User;
begin
  retRegister:= userRegister('test003', '123456', 'test003', 'test003@test.com');
  WriteLn(retRegister);

  retLogin := userLogin('test003', '123456');
  WriteLn(retLogin.ToString);
  retLogin.Free;
end;

end.

