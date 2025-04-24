import json
import random
import requests
import time


URL = "http://localhost:8086/api/person/check"


def query():

    vkn = random.randint(1000000000, 9999999999)

    try:
        with open("Tc_Numbers.json", "r", encoding="utf-8") as f:
            tc_numbers = json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        print(" Hata: Kimlik dosyası bulunamadı veya hatalı format!")
        exit()

    if isinstance(tc_numbers, list) and len(tc_numbers) > 0:
        tc = random.choice(tc_numbers)
    elif isinstance(tc_numbers, str):
        tc = tc_numbers
    else:
        print(" Hata: Tc_Numbers.json formatı yanlış!")
        exit()


    data = {"tcKimlikNumber": tc, "corporationVkn": vkn}
    return data


headers = {
    "Authorization": "Bearer YOUR_ACCESS_TOKEN",
    "Content-Type": "application/json"
}

while True:
    try:

        payload = query()
        response = requests.post(URL, json=payload, headers=headers)

        if response.status_code == 200:
            print(" Başarıyla sorgulandı!")
            print("Yanıt:", response.json())
        else:
            print(f" Hata! HTTP Durum Kodu: {response.status_code}")
            print(response.text)

    except requests.exceptions.ConnectionError:
        print(" Bağlantı hatası!")
    except requests.exceptions.RequestException as e:
        print(f" Bir hata oluştu: {e}")

    time.sleep(1)
