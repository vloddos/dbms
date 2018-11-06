/* Generated By:JavaCC: Do not edit this line. SqlParserTokenManager.java */
package com.sql_parser.grammar;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import dbms.*;

/** Token Manager. */
public class SqlParserTokenManager implements SqlParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x200000L) != 0L)
         {
            jjmatchedKind = 27;
            return 25;
         }
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 27;
            return 15;
         }
         if ((active0 & 0x5f3c00L) != 0L)
         {
            jjmatchedKind = 27;
            return 32;
         }
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 27;
            return 12;
         }
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 27;
            return 19;
         }
         return -1;
      case 1:
         if ((active0 & 0x200000L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 1;
            return 24;
         }
         if ((active0 & 0x5fbe00L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 1;
            return 32;
         }
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 1;
            return 14;
         }
         return -1;
      case 2:
         if ((active0 & 0x7ffe00L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 2;
            return 32;
         }
         return -1;
      case 3:
         if ((active0 & 0x6fee00L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 3;
            return 32;
         }
         if ((active0 & 0x1000L) != 0L)
            return 32;
         return -1;
      case 4:
         if ((active0 & 0x6bc600L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 4;
            return 32;
         }
         return -1;
      case 5:
         if ((active0 & 0x234600L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 5;
            return 32;
         }
         return -1;
      case 6:
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 6;
            return 32;
         }
         if ((active0 & 0x4000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 27;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 7:
         if ((active0 & 0x4000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 27;
               jjmatchedPos = 5;
            }
            return -1;
         }
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 7;
            return 32;
         }
         return -1;
      case 8:
         if ((active0 & 0x4000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 27;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 9:
         if ((active0 & 0x4000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 27;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 10:
         if ((active0 & 0x4000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 27;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 13:
         jjmatchedKind = 2;
         return jjMoveStringLiteralDfa1_0(0x8L);
      case 40:
         return jjStopAtPos(0, 29);
      case 41:
         return jjStopAtPos(0, 30);
      case 44:
         return jjStopAtPos(0, 31);
      case 59:
         return jjStopAtPos(0, 28);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa1_0(0xc00L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa1_0(0x60000L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      default :
         return jjMoveNfa_0(2, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 10:
         if ((active0 & 0x8L) != 0L)
            return jjStopAtPos(1, 3);
         break;
      case 65:
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x280400L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L);
      case 72:
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x440000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x2a00L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 88:
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 66:
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x500200L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x220000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x42800L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x100000L) != 0L)
            return jjStopAtPos(3, 20);
         break;
      case 65:
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x600L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x24000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 80:
      case 112:
         return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 84:
      case 116:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(3, 12, 32);
         break;
      case 85:
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(4, 11);
         else if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(4, 13);
         else if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(4, 18);
         break;
      case 66:
      case 98:
         return jjMoveStringLiteralDfa5_0(active0, 0x400L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa5_0(active0, 0x690000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x8200L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(5, 15);
         else if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(5, 19);
         else if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(5, 22);
         break;
      case 65:
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x400L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa6_0(active0, 0x200L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa6_0(active0, 0x200000L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x34000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(6, 9);
         else if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(6, 16);
         else if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(6, 17);
         else if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(6, 21);
         return jjMoveStringLiteralDfa7_0(active0, 0x4000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa7_0(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 69:
      case 101:
         return jjMoveStringLiteralDfa8_0(active0, 0x400L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(8, 10);
         break;
      case 78:
      case 110:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 84:
      case 116:
         return jjMoveStringLiteralDfa10_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 79:
      case 111:
         return jjMoveStringLiteralDfa11_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(11, 14);
         break;
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 49;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 24:
               case 32:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjCheckNAdd(32);
                  break;
               case 15:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjCheckNAdd(32);
                  break;
               case 14:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjCheckNAdd(32);
                  break;
               case 12:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjCheckNAdd(32);
                  break;
               case 25:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjCheckNAdd(32);
                  break;
               case 2:
                  if ((0x3ff600000000000L & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  else if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 3;
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCheckNAddStates(0, 6);
                  }
                  else if (curChar == 46)
                     jjCheckNAdd(28);
                  else if (curChar == 45)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 19:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjCheckNAdd(32);
                  break;
               case 0:
                  if (curChar != 45)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(1);
                  break;
               case 1:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(1);
                  break;
               case 3:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 4:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 5:
                  if (curChar == 42)
                     jjCheckNAddStates(7, 9);
                  break;
               case 6:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(7, 5);
                  break;
               case 7:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(7, 5);
                  break;
               case 8:
                  if (curChar == 47 && kind > 8)
                     kind = 8;
                  break;
               case 9:
                  if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 27:
                  if (curChar == 46)
                     jjCheckNAdd(28);
                  break;
               case 28:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAddTwoStates(28, 29);
                  break;
               case 30:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(31);
                  break;
               case 31:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAdd(31);
                  break;
               case 33:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddStates(0, 6);
                  break;
               case 34:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAdd(34);
                  break;
               case 35:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(35, 36);
                  break;
               case 36:
                  if (curChar == 46)
                     jjCheckNAdd(37);
                  break;
               case 37:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAddTwoStates(37, 38);
                  break;
               case 39:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(40);
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAdd(40);
                  break;
               case 41:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(41, 42);
                  break;
               case 43:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(44);
                  break;
               case 44:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAdd(44);
                  break;
               case 45:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAddTwoStates(45, 46);
                  break;
               case 47:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(48);
                  break;
               case 48:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 25)
                     kind = 25;
                  jjCheckNAdd(48);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 24:
                  if ((0x7fffffe87ffffffL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  if ((0x4000000040000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 15:
                  if ((0x7fffffe87ffffffL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  if ((0x400000004000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 14:
                  if ((0x7fffffe87ffffffL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  if ((0x10000000100000L & l) != 0L)
                  {
                     if (kind > 23)
                        kind = 23;
                  }
                  break;
               case 12:
                  if ((0x7fffffe87ffffffL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  if ((0x10000000100L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 25:
                  if ((0x7fffffe87ffffffL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  if ((0x200000002L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 24;
                  break;
               case 2:
                  if ((0x7fffffe87ffffffL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  if ((0x40000000400000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 25;
                  else if ((0x100000001000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 19;
                  else if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 15;
                  else if ((0x800000008L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 19:
                  if ((0x7fffffe87ffffffL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     jjCheckNAdd(32);
                  }
                  if ((0x800000008000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 1:
                  if (kind > 7)
                     kind = 7;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 4:
                  jjCheckNAddTwoStates(4, 5);
                  break;
               case 6:
               case 7:
                  jjCheckNAddTwoStates(7, 5);
                  break;
               case 10:
                  if ((0x4000000040000L & l) != 0L && kind > 23)
                     kind = 23;
                  break;
               case 11:
               case 21:
                  if ((0x200000002L & l) != 0L)
                     jjCheckNAdd(10);
                  break;
               case 13:
                  if ((0x800000008L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 16:
                  if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if ((0x8000000080L & l) != 0L && kind > 23)
                     kind = 23;
                  break;
               case 18:
                  if ((0x400000004000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 20:
                  if ((0x100000001000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 22:
                  if ((0x10000000100L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 23:
                  if ((0x800000008L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 26:
                  if ((0x40000000400000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 25;
                  break;
               case 29:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(10, 11);
                  break;
               case 32:
                  if ((0x7fffffe87ffffffL & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjCheckNAdd(32);
                  break;
               case 38:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(12, 13);
                  break;
               case 42:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(14, 15);
                  break;
               case 46:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(16, 17);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 4:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 6:
               case 7:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(7, 5);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 49 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   34, 35, 36, 41, 42, 45, 46, 5, 6, 8, 30, 31, 39, 40, 43, 44, 
   47, 48, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, "\73", "\50", "\51", "\54", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xfbfffe01L, 
};
static final long[] jjtoSkip = {
   0x1feL, 
};
static final long[] jjtoSpecial = {
   0x180L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[49];
private final int[] jjstateSet = new int[98];
protected char curChar;
/** Constructor. */
public SqlParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public SqlParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 49; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }

   try { input_stream.backup(0);
      while ((curChar < 64 && (0x100000600L & (1L << curChar)) != 0L) || 
             (curChar >> 6) == 1 && (0x10000000L & (1L << (curChar & 077))) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         matchedToken.specialToken = specialToken;
         return matchedToken;
      }
      else
      {
         if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
         {
            matchedToken = jjFillToken();
            if (specialToken == null)
               specialToken = matchedToken;
            else
            {
               matchedToken.specialToken = specialToken;
               specialToken = (specialToken.next = matchedToken);
            }
         }
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
