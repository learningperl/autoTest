on error resume next
Set args = WScript.Arguments
If args.Count = 1 Then
	name= WScript.Arguments(0)
	Prosses(name)
End If


'---------------------------------------------------------------------------------------------------------------------------
'结束指定进程
Function Prosses(name)
	set wmi=getobject("winmgmts:\\.\root\cimv2")
	set col=wmi.execquery("select * from win32_process")
	for each pro in col
		if pro.name=name then 
			set pro1 = pro
			pro1.terminate
		end if
	next 
End Function
'---------------------------------------------------------------------------------------------------------------------------