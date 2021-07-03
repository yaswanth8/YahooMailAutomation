Dim objShell
Set objShell = WScript.CreateObject( "WScript.Shell" )
objShell.Run("""C:\Program Files (x86)\ExpressVPN\expressvpn-ui\ExpressVPN.exe""")
WScript.Sleep 3000
objShell.sendkeys"{TAB}"
WScript.Sleep 3000
objShell.sendkeys"{ENTER}"
WScript.Sleep 4000
objShell.sendkeys"%{F4}"
Set objShell = Nothing
