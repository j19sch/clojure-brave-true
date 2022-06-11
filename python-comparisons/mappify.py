rows = [["Edward Cullen", "10"], ["Bella Swan", "0"], ["Charlie Swan", "0"],
	["Jacob Black", "3"], ["Carlisle Cullen", "6"]]

vamp_keys = ["name", "glitter-index"]

# output1 = []
# for row in rows:
# 	the_dict = {
# 		"name": row[0],
# 		"glitter_index": int(row[1])
# 	}
# 	output.append(the_dict)

# print(output1)


# output2 = [ {"name": row[0], "glitter_index": int(row[1])} for row in rows]
# print(output2)


output = [
	{
		vamp_keys[0]: row[0],
		vamp_keys[1]: int(row[1])
	}
	for row in rows
]

print(output)
