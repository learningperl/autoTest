
on error resume next
Set args = WScript.Arguments
If args.Count = 1 Then
	key = WScript.Arguments(0)
	Press(key)
End If

Function Press(key)
	Set objShell = CreateObject("Wscript.Shell")
	key = "{" + key + "}"
	objShell.SendKeys key
End Function