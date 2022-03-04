import RPi.GPIO as GPIO
import time
'''
----------------------------------
CONFIGURATION
----------------------------------
'''

GPIO.setmode(GPIO.BCM)
GPIO.setup(17,GPIO.OUT)

'''
----------------------------------
main activity
----------------------------------
'''

while True:
   GPIO.output(17,GPIO.HIGH)
   time.sleep(1)
   GPIO.output(17,GPIO.LOW)
   time.sleep(1)