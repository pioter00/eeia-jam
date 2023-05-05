def lineformatter(line):
    line = line.strip("\n")
    line = line.replace('(', '')
    line = line.replace(',', '')
    line = line.replace(')', '')
    return line

f = open("src/coordinates.txt", "r")
fw = open("src/back_coords.txt", "w")
lines = f.readlines()
flag = -1
flag_curve = -1
i = 0
for line in lines:
    if flag != -1:
        flag += 1
    if flag_curve != -1:
        flag_curve += 1
        
    if line.startswith("//") and "Zw" in line:
        flag = 0
    if line.startswith("//") and "2" in line:
        flag_curve = 0
    if not line.startswith("//") and (flag == 2 or flag == -1) and (flag_curve == 4 or flag_curve == -1):
        if flag == 2:
            flag = -1
            continue
        if flag_curve == 4:
            flag_curve = -1
            continue
        if i % 4 == 0:
            line = lineformatter(line)
            values = line.split(" ")
            fw.write('(' + str(int(values[0]) - 4) + ", " + str(int(values[1])) + ") " + "3" + " " + "3" + " " + "0" + '\n')
            fw.write('(' + str(int(values[0])) + ", " + str(int(values[1]) - 4) + ") " + "1" + " " + "1" + " " + "0" + '\n')
        if i % 4 == 1:
            line = lineformatter(line)
            values = line.split(" ")
            
            fw.write('(' + str(int(values[0]) + 4) + ", " + str(int(values[1])) + ") " + "2" + " " + "2" + " " + "0" + '\n')
            fw.write('(' + str(int(values[0])) + ", " + str(int(values[1]) - 4) + ") " + "0" + " " + "0" + " " + "0" + '\n')
        if i % 4 == 2:
            line = lineformatter(line)
            values = line.split(" ")
            fw.write('(' + str(int(values[0]) - 4) + ", " + str(int(values[1])) + ") " + "0" + " " + "0" + " " + "0" + '\n')
            fw.write('(' + str(int(values[0])) + ", " + str(int(values[1]) + 4) + ") " + "2" + " " + "2" + " " + "0" + '\n')
        if i % 4 == 3:
            line = lineformatter(line)
            values = line.split(" ")
            fw.write('(' + str(int(values[0]) + 4) + ", " + str(int(values[1])) + ") " + "1" + " " + "1" + " " + "0" + '\n')
            fw.write('(' + str(int(values[0])) + ", " + str(int(values[1]) + 4) + ") " + "3" + " " + "3" + " " + "0" + '\n')
        i += 1

print("Generating back roads: Done!")
fw.close() 
f.close()