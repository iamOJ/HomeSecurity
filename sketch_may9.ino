
int lock = 9;          //pin 9 on Arduino
char final[4];         //Characters the Arduino will receive
char correct[4] = {'1','2','3','4'};    //User-Defined Password
int pass_correct = 0;          //Does Password match, 0=false 1=true

void setup()
{
  pinMode(lock, OUTPUT);
  Serial.begin(9600);
  digitalWrite(lock, LOW);  //By default, lock is active(locked)
}

void loop()
{
  while(Serial.available()) 
  {
    for(int i=0; i<4; i++)   //While data is available read 4 bytes
    {
       final[i] = Serial.read();  //Read 4 bytes into the array labled "final"
    }
  
    for(int i=0; i<4; i++)
    {
       if(final[i]==correct[i]) //Compare each char received to each car in our password in order
       {
          //Serial.print(final[i]);
          pass_correct++;   //If we compare two chars and they match, set the pass_correct variable to true(1)
          //Serial.print(pass_correct);
       }
       else
       {
          //Serial.print(final[i]);
          //pass_correct = 0;  //if the two compared chars do NOT match, set pass_correct variable to false(0)
          //Serial.print("Ye wala");
          //Serial.print(pass_correct);
          Serial.println("Incorrect Password");
          break;   //End loop and stop comparing chars
       }
    }
}

if(pass_correct==4)  //If all chars compared match, deactivate(unlock) the lock for 5 seconds
{
    Serial.println("Unlocked");
    digitalWrite(lock, HIGH);
    delay(20000);
    Serial.println("Locked");
    pass_correct = 0;
}
else
{
  //Serial.println("Locked");
digitalWrite(lock, LOW); //Else if there was not a complete match, keep the lock high(locked)
}


/* FOR TESTING
Serial.print(final[0]);Serial.print(final[1]);Serial.print(final[2]);Serial.print(final[3]);
Serial.print(" | ");
Serial.print(correct[0]);Serial.print(correct[1]);Serial.print(correct[2]);Serial.print(correct[3]);
Serial.print(" ");
Serial.print(pass_correct);
Serial.println("");
*/
delay(500);
}
