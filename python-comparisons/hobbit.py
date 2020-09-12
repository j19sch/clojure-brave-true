import random

ASYM_HOBBIT_BODY_PARTS = [
	{"name": "head", "size": 3},
	{"name": "left-eye", "size": 1},
	{"name": "left-ear", "size": 1},
	{"name": "mouth", "size": 1},
	{"name": "nose", "size": 1},
	{"name": "neck", "size": 2},
	{"name": "left-shoulder", "size": 3},
	{"name": "left-upper-arm", "size": 3},
	{"name": "chest", "size": 10},
	{"name": "back", "size": 10},
	{"name": "left-forearm", "size": 3},
	{"name": "abdomen", "size": 6},
	{"name": "left-kidney", "size": 1},
	{"name": "left-hand", "size": 2},
	{"name": "left-knee", "size": 2},
	{"name": "left-thigh", "size": 4},
	{"name": "left-lower-leg", "size": 3},
	{"name": "left-achilles", "size": 1},
	{"name": "left-foot", "size": 2}
]

def symmetrize_body_parts(asym_body_parts):
	sym_body_parts = []
	for part in asym_body_parts:
		if part["name"].startswith("left-"):
			# # first version copied via dictionary comprehension
			# other_one = {k:v for k,v in part.items()}
			# # second version copied via copy
			# other_one = part.copy()
			# # replacing left- with right- in the copy
			# other_one["name"] = other_one["name"].replace("left-", "right-")
			# # dictionary unpacking and change value, more implicit though
			other_one = {**part, "name": part["name"].replace("left-", "right-")}
			sym_body_parts.extend([part, other_one])
		else:
			sym_body_parts.append(part)

	return sym_body_parts


def hit(asym_body_parts):
	sym_parts = symmetrize_body_parts(asym_body_parts)
	sum_of_all_parts = sum([_["size"] for _ in sym_parts])

	counter = 0
	target = random.randint(0, sum_of_all_parts - 1)
	# Python's randint() has an exclusive upper
	# Different from clj's rand

	for part in sym_parts:
		"""
		Clojure version does the same, but less obvious because
		loop with binding to size and the recur
		"""
		# intermediate version, same but different
		# counter += part["size"]
		# if target < counter:
			# return part

		counter += part["size"]
		if counter > target:
			return part


if __name__ == "__main__":
	print(hit(ASYM_HOBBIT_BODY_PARTS))
