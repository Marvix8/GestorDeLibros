; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Gestor de Libros"
#define MyAppVersion "1.0"
#define MyAppExeName "Gestor de Libros.jar"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{29AE631A-2EEE-4C2F-B6DB-14F0830DA9C2}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
DefaultDirName={pf}\{#MyAppName}
DisableProgramGroupPage=yes
OutputBaseFilename=setupGestorLibros
Compression=lzma
SolidCompression=yes

[Languages]
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Users\Octi\workspaceAnSoft\GestorDeLibros\Gestor de Libros.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Octi\workspaceAnSoft\GestorDeLibros\database.txt"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Octi\workspaceAnSoft\GestorDeLibros\resources\gestor.ico"; DestDir: "{app}"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\gestor.ico"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\gestor.ico"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: shellexec postinstall skipifsilent

