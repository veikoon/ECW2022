import time
import os
import ast
import random
import sys
import socket
import base64


if len(sys.argv) != 3:
	print("Usage : python3 jigsaw.py [IP] [PORT]")
	exit()

# Ouverture de la connexion TCP
TCP_IP = sys.argv[1]
TCP_PORT = int(sys.argv[2])
BUFFER_SIZE = 1024
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))
print(str(s.recv(BUFFER_SIZE), "utf-8"))

# Fonction d'envoie de message
def sendmessage(reponse):
	print(reponse)
	s.send(reponse.encode())
	time.sleep(0.1) # On attend la réponse du serveur
	data = s.recv(BUFFER_SIZE)
	output = str(data, "utf-8")
	print(output)
	return output

# Question 1 et 2 :
sendmessage("yes")
q3 = sendmessage("10/28")

# Question 3 :
def question3(calcul):
	result = eval(calcul) # La fonction eval interprète le calcul depuis la string
	return sendmessage(str(result)), result

calc_string = q3.split('\n')[4] # On ne récupère que la ligne contenant le calcul
q4, result = question3(calc_string)

# Question 4 :
def question4(question):
	for base in ["base16", "base32", "base64", "base85"]:
		try: # Si le message n'arrive pas à être correctement lu c'est que l'encodage est mauvais
			if base == "base16":
				decoded = base64.b16decode(question)
			elif base == "base32":
				decoded = base64.b32decode(question)
			elif base == "base64":
				decoded = base64.b64decode(question)
			elif base == "base85":
				decoded = base64.b85decode(question)
			decoded = decoded.decode('utf-8')
		except:
			pass
		else:
			break
	return base, decoded

encoded_string = q4.split('\n')[3].split('\'')[1] # On ne récupère que la string encodé
base_answer,_ = question4(encoded_string)
sendmessage(base_answer)

# Question 5
answers = ["yes","10/28"]
answers.append(str(result))
answers.append(base_answer)
q6 = sendmessage(",".join(answers))

# Question 6
def question6(list_index):
	repeat_list = ast.literal_eval(list_index) # On interpète la liste depuis la string
	answers_repeat = [answers[i-1] for i in repeat_list] # On selectionne nos réponses
	return sendmessage(",".join(answers_repeat))

list_index = q6.split('\n')[4] # On récupère la string du tableau d'input
q7 = question6(list_index)

# Question 7
if "Easy maths" in q7:					# Question 3 - Operation mathématique
	q8,_ = question3(q7.split('\n')[5])
elif "I forgot everything" in q7:		# Question 5 - Liste de réponse
	q8 = sendmessage(",".join(answers))
elif "I did not understand" in q7:		# Question 6 - Liste de réponse avec index
	q8 = question6(q7.split('\n')[5])
else:									# Question 4 - Question encodé
	decoded,_ = question4(q7.split('\n')[4].split('\'')[1])
	q8 = sendmessage(decoded)

# Question 8

# Fonction applicant une rotation alphabétique de n sur un mot donné
def rot_alpha(n):
	from string import ascii_lowercase as lc, ascii_uppercase as uc
	lookup = str.maketrans(lc + uc, lc[n:] + lc[:n] + uc[n:] + uc[:n])
	return lambda s: s.translate(lookup)

rotated_word = q8.split('\n')[5] # On récupère le mot encodé
dictionary = open("words.txt",'r').read().splitlines() # On charge le dictionnaire

q9 = ""
for i in range(26):
	new_rotated_word = rot_alpha(i)(rotated_word)
	if new_rotated_word in dictionary:
		q9 = sendmessage(new_rotated_word)
		break

# Si le mot n'était pas le bon ou si le mot n'est pas présent dans le dictionnaire
if not "9." in q9:
	exit()

# Question 9

# Fonction retournant les indexes d'une lettre dans un mot
def find(string, char):
	return [i for i, ltr in enumerate(string) if ltr == char]

# Fonction cherchant les similitudes d'occurance de lettres entre le mot et le dictionnaire
def findindict(word, english_dict):
	listefinale = []
	duplicate = []

	# On cherche tous les indexes de répétition de lettres dans un mot
	for letter in word:
		indexes = find(word, letter)
		if len(indexes) >= 2 and indexes not in duplicate:
			duplicate.append(indexes)

	# On compare ces indexes avec tous les mots du dictionnaire
	for english_word in english_dict:
		if len(word) == len(english_word):
			isgood = True
			for d in duplicate:
				for i in range(1,len(d)):
					if(english_word[d[0]] != english_word[d[i]]):
						isgood = False
			if isgood:
				listefinale.append(english_word)

	# On retourne une des solutions possible aléatoirement
	return listefinale[random.randrange(0,len(listefinale))]

cyphered_word = q9.split('\n')[5]
word = findindict(cyphered_word, dictionary)
q10 = sendmessage(word)

if "bye" in q10:
	exit()

s.close()