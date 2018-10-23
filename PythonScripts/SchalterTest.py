import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

i = True

def button_callback(channel):
    global i
    if i:
        f = open("/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt", "w")
        f.write("1")
        f.close()
        i = False
        print("1")
        time.sleep(5)
        print("rdy")
        
    elif not i:
        f = open("/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt", "w")
        f.write("0")
        f.close()
        i = True
        print ("0")
        time.sleep(5)
        print("rdy")
        

GPIO.setup(24, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
GPIO.add_event_detect(24,GPIO.RISING,callback=button_callback)

message = input("Press enter to quit \n\n")

GPIO.cleanup()
