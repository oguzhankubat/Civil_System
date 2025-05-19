import json
import random
import requests
import time

URL = "http://localhost:8086/api/person/check"


def query():

    vkn = random.randint(1000000000, 9999999999)


    try:
        with open("persons.json", "r", encoding="utf-8") as f:
            total_lines = sum(1 for _ in f)
    except FileNotFoundError:
        print("Hata: persons.json dosyası bulunamadı!")
        exit()


    random_line = random.randint(0, total_lines - 1)


    with open("persons.json", "r", encoding="utf-8") as f:
        for current_line, line in enumerate(f):
            if current_line == random_line:
                try:
                    person = json.loads(line.strip())


                    if isinstance(person, list):
                        person = random.choice(person)


                    tc = person["tcKimlikNumber"]
                    break
                except (json.JSONDecodeError, KeyError, IndexError):
                    print(f"Uyarı: Geçersiz JSON veya eksik tcKimlikNumber! -> Satır {current_line}")
                    return None


    if not tc:
        print("Hata: tcKimlikNumber bulunamadı!")
        return None


    params = {"tcKimlikNumber": tc, "corporationVkn": vkn}
    return params



headers = {
    "Authorization": "Bearer YOUR_ACCESS_TOKEN"
}


while True:
    try:

        params = query()


        if params is None:
            continue


        response = requests.get(URL, params=params, headers=headers)

        if response.status_code == 200:
            print("Başarıyla sorgulandı!")
            print("Yanıt:", response.json())
        else:
            print(f"Hata! HTTP Durum Kodu: {response.status_code}")
            print(response.text)

    except requests.exceptions.ConnectionError:
        print("Bağlantı hatası!")
    except requests.exceptions.RequestException as e:
        print(f"Bir hata oluştu: {e}")

