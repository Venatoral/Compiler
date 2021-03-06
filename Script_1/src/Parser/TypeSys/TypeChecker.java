package Parser.TypeSys;


import java.util.*;
import Interpreter.*;
import Parser.*;
import Parser.TypeSys.T_BasicType.en_BType;

public class TypeChecker {	
	
	static public T_Type checkOptFour(CodeGenerator codegen, String opt, T_Type type1, T_Type type2) throws TypeCheckException{//a+b a-b a*b a/b
		if(type1 instanceof T_BasicType && type2 instanceof T_BasicType){
			T_BasicType t1=(T_BasicType)type1;
			T_BasicType t2=(T_BasicType)type2;
			if(opt.equals("+")&&
					(t1.getTypeB()==en_BType.t_string||t2.getTypeB()==en_BType.t_string)){
				return codegen.getTypeInSymTb("string");
			}else if(opt.equals("+")||opt.equals("-")){
				if((t1.getTypeB()==en_BType.t_char||t1.getTypeB()==en_BType.t_int)
						&&(t2.getTypeB()==en_BType.t_char||t2.getTypeB()==en_BType.t_int)){
					return codegen.getTypeInSymTb("int");
				}
			}
			return checkBscFour(codegen,t1,t2);
		}
		//maybe opt reload in future
		//search in codegen if registered reloaded operator for type1 
		else
			throw new TypeCheckException( "failed type checking: "+type1.getTypeSig()+" "+type2.getTypeSig()+" opt four");
	}
	
	static public T_Type checkBscFour(CodeGenerator codegen, T_BasicType type1, T_BasicType type2)throws TypeCheckException{//arithmetic int double
		if(type1.getTypeB()==en_BType.t_int&&type2.getTypeB()==en_BType.t_int){
			return codegen.getTypeInSymTb("int");
		}else if(type1.getTypeB()==en_BType.t_double||type2.getTypeB()==en_BType.t_double){
			return codegen.getTypeInSymTb("double");
		}
		throw new TypeCheckException("failed type checking: "+type1.getTypeSig()+" "+type2.getTypeSig()+" opt plus");
	}
	
	static public T_Type checkOptCmp(CodeGenerator codegen, T_Type type1, T_Type type2) throws TypeCheckException{
		if(type1 instanceof T_BasicType && type2 instanceof T_BasicType){
			T_BasicType t1=(T_BasicType)type1;
			T_BasicType t2=(T_BasicType)type2;
			return checkBscCmp(codegen,t1,t2);
		}
		//check if reload compare operator for type1 and type2
		throw new TypeCheckException("failed type checking: "+type1.getTypeSig()+" "+type2.getTypeSig()+" opt cmp");
	}
	
	static public T_Type checkBscCmp(CodeGenerator codegen, T_BasicType t1, T_BasicType t2)throws TypeCheckException{//a>b >= < <=
		if((t1.getTypeB()==en_BType.t_int||t2.getTypeB()==en_BType.t_double)
				&&(t2.getTypeB()==en_BType.t_int||t2.getTypeB()==en_BType.t_double)){
			return codegen.getTypeInSymTb("bool");
		}else if((t1.getTypeB()==en_BType.t_char||t1.getTypeB()==en_BType.t_int)
				&&(t2.getTypeB()==en_BType.t_char||t2.getTypeB()==en_BType.t_int)){
			return codegen.getTypeInSymTb("bool");
		}
		throw new TypeCheckException("failed type checking: "+t1.getTypeSig()+" "+t2.getTypeSig()+" opt plus");
		
	}
	
	static public boolean checkOptEq(CodeGenerator codegen, T_Type type1, T_Type type2)throws TypeCheckException{// a==b !=
		if(type1 instanceof T_BasicType && type2 instanceof T_BasicType){
			T_BasicType t1=(T_BasicType)type1;
			T_BasicType t2=(T_BasicType)type2;
			checkBscEq(codegen,t1,t2);
		}else{
			if(type1.isEqType(type2))
				return true;
		}
		throw new TypeCheckException("failed type checking: "+type1.getTypeSig()+" "+type2.getTypeSig()+" opt equality");
	}
	
	static public boolean checkBscEq(CodeGenerator codegen, T_BasicType t1, T_BasicType t2)throws TypeCheckException{// a==b !=
		if((t1.getTypeB()==en_BType.t_int||t2.getTypeB()==en_BType.t_double)
				&&(t2.getTypeB()==en_BType.t_int||t2.getTypeB()==en_BType.t_double)){
			return true;
		}else if((t1.getTypeB()==en_BType.t_char||t1.getTypeB()==en_BType.t_int)
				&&(t2.getTypeB()==en_BType.t_char||t2.getTypeB()==en_BType.t_int)){
			return true;
		}else if(t1.getTypeSig().equals(t2.getTypeSig())){
			return true;
		}
		throw new TypeCheckException("failed type checking: "+t1.getTypeSig()+" "+t2.getTypeSig()+" opt equality");
	}
	
	static public boolean checkOptInc(CodeGenerator codegen, T_Type type1)throws TypeCheckException{//a++ ++a 
		if(type1 instanceof T_BasicType){
			T_BasicType t1=(T_BasicType)type1;
			if(t1.getTypeB()==en_BType.t_int||t1.getTypeB()==en_BType.t_char){
				return true;
			}
		}else{
			//check for opt reload
		}
		throw new TypeCheckException("failed type checking: "+type1.getTypeSig()+" opt increment");
	}
	
	static public boolean checkOptMinus(CodeGenerator codegen, T_Type type1)throws TypeCheckException{//a++ ++a 
		if(type1 instanceof T_BasicType){
			T_BasicType t1=(T_BasicType)type1;
			if(t1.getTypeB()==en_BType.t_int||t1.getTypeB()==en_BType.t_double){
				return true;
			}
		}else{
			//check for opt reload
		}
		throw new TypeCheckException("failed type checking: "+type1.getTypeSig()+" opt +/-");
	}
	
	static public boolean checkCast(CodeGenerator codegen, T_Type type1, T_Type type2) throws TypeCheckException{//type1 xx=type2 xx
		if(type1.canCastFrom(codegen, type2)) return true;
		throw new TypeCheckException("failed type checking: "+type1.getTypeSig()+" type cast");
	}	
}
