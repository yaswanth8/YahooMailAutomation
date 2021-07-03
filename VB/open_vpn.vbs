Function launchVpn(city)
Dim objShell
Set objShell = WScript.CreateObject( "WScript.Shell" )
objShell.Run("""C:\Program Files (x86)\ExpressVPN\expressvpn-ui\ExpressVPN.exe""")
WScript.Sleep 1000
objShell.sendkeys "{TAB}"
WScript.Sleep 1000
objShell.sendkeys "{TAB}"
WScript.Sleep 1000
objShell.sendkeys"{ENTER}"
WScript.Sleep 1000
objShell.sendkeys"{TAB}"
WScript.Sleep 1000
objShell.sendkeys"{ENTER}"
WScript.Sleep 1000
objShell.sendkeys(city)
WScript.Sleep 1000
objShell.sendkeys"{TAB}"
WScript.Sleep 1000
objShell.sendkeys"{UP}"
WScript.Sleep 1000
objShell.sendkeys"{UP}"
WScript.Sleep 1000
objShell.sendkeys"{UP}"
WScript.Sleep 1000
objShell.sendkeys"{ENTER}"
WScript.Sleep 1000
objShell.sendkeys"%{F4}"
WScript.Sleep 1000
objShell.sendkeys"+{TAB}"
WScript.Sleep 1000
objShell.sendkeys"{ENTER}"
WScript.Sleep 8000
objShell.sendkeys"%{F4}"
Set objShell = Nothing
End Function
city = WScript.Arguments.Item(0)
Call launchVpn(city)

