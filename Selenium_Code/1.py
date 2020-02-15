x =input()
y = len(x)
i=1
flag= 0
for i in range (1,y-1):
    for j in range(i+len(x[:i]),y):
        a = x[:i]
        b = x[i:j]
        c = x[j:]
        e=int(a)
        f=int(b)
        g=int(c)
        if(e==f and e*f==g):
            flag=1
            break
    if(flag==1):
        break

if (flag==1):
    print(str(e)+'*'+str(f)+'='+str(g))
else:
    print("Impossible")