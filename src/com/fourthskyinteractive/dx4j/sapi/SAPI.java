package com.fourthskyinteractive.dx4j.sapi;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.ann.Runtime;
import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.TypedPointer;
import org.bridj.ann.Library;
import org.bridj.cpp.com.COMRuntime;

@Library("sapi") 
@Runtime(COMRuntime.class) 
public class SAPI {
	static {
		BridJ.register();
	}

	public static final String SPREG_USER_ROOT          		= "HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Speech";
	public static final String SPREG_LOCAL_MACHINE_ROOT 		= "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech";
	public static final String SPCAT_AUDIOOUT         			= "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\AudioOutput";
	public static final String SPCAT_AUDIOIN          			= "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\AudioInput";
	public static final String SPCAT_VOICES           			= "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\Voices";
	public static final String SPCAT_RECOGNIZERS      			= "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\Recognizers";
	public static final String SPCAT_APPLEXICONS      			= "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\AppLexicons";
	public static final String SPCAT_PHONECONVERTERS  			= "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\PhoneConverters";
	public static final String SPCAT_RECOPROFILES     			= "HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Speech\\RecoProfiles";
	public static final String SPMMSYS_AUDIO_IN_TOKEN_ID        = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\AudioInput\\TokenEnums\\MMAudioIn\\";
	public static final String SPMMSYS_AUDIO_OUT_TOKEN_ID       = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Speech\\AudioOutput\\TokenEnums\\MMAudioOut\\";
	public static final String SPCURRENT_USER_LEXICON_TOKEN_ID  = "HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Speech\\CurrentUserLexicon";
	public static final String SPCURRENT_USER_SHORTCUT_TOKEN_ID = "HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Speech\\CurrentUserShortcut";

	public static final String CLSID_SpInprocRecognizer = "41B89B6B-9399-11D2-9623-00C04F8EE628";
	public static final String CLSID_SpSharedRecognizer = "3BEE4890-4FE9-4A37-8C1E-5E7E12791C1F";
	
	public class SPSTATEHANDLE extends TypedPointer {
		public SPSTATEHANDLE(Pointer<?> ptr) {
			super(ptr);
		}
	}

	/// enum values
	public enum SPADAPTATIONRELEVANCE implements IntValuedEnum<SPADAPTATIONRELEVANCE > {
		SPAR_Unknown(0),
		SPAR_Low(1),
		SPAR_Medium(2),
		SPAR_High(3);
		SPADAPTATIONRELEVANCE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPADAPTATIONRELEVANCE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPADAPTATIONRELEVANCE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	/// enum values
	public enum SPGRAMMARWORDTYPE implements IntValuedEnum<SPGRAMMARWORDTYPE > {
		SPWT_DISPLAY(0),
		SPWT_LEXICAL(((int)SAPI.SPGRAMMARWORDTYPE.SPWT_DISPLAY.value() + 1)),
		SPWT_PRONUNCIATION(((int)SAPI.SPGRAMMARWORDTYPE.SPWT_LEXICAL.value() + 1)),
		SPWT_LEXICAL_NO_SPECIAL_CHARS(((int)SAPI.SPGRAMMARWORDTYPE.SPWT_PRONUNCIATION.value() + 1));
		SPGRAMMARWORDTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPGRAMMARWORDTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPGRAMMARWORDTYPE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	/// enum values
	public enum SPEVENTENUM implements IntValuedEnum<SPEVENTENUM > {
		SPEI_UNDEFINED(0),
		SPEI_START_INPUT_STREAM(1),
		SPEI_END_INPUT_STREAM(2),
		SPEI_VOICE_CHANGE(3),
		SPEI_TTS_BOOKMARK(4),
		SPEI_WORD_BOUNDARY(5),
		SPEI_PHONEME(6),
		SPEI_SENTENCE_BOUNDARY(7),
		SPEI_VISEME(8),
		SPEI_TTS_AUDIO_LEVEL(9),
		SPEI_TTS_PRIVATE(15),
		SPEI_MIN_TTS(1),
		SPEI_MAX_TTS(15),
		SPEI_END_SR_STREAM(34),
		SPEI_SOUND_START(35),
		SPEI_SOUND_END(36),
		SPEI_PHRASE_START(37),
		SPEI_RECOGNITION(38),
		SPEI_HYPOTHESIS(39),
		SPEI_SR_BOOKMARK(40),
		SPEI_PROPERTY_NUM_CHANGE(41),
		SPEI_PROPERTY_STRING_CHANGE(42),
		SPEI_FALSE_RECOGNITION(43),
		SPEI_INTERFERENCE(44),
		SPEI_REQUEST_UI(45),
		SPEI_RECO_STATE_CHANGE(46),
		SPEI_ADAPTATION(47),
		SPEI_START_SR_STREAM(48),
		SPEI_RECO_OTHER_CONTEXT(49),
		SPEI_SR_AUDIO_LEVEL(50),
		SPEI_SR_RETAINEDAUDIO(51),
		SPEI_SR_PRIVATE(52),
		SPEI_RESERVED4(53),
		SPEI_RESERVED5(54),
		SPEI_RESERVED6(55),
		SPEI_MIN_SR(34),
		SPEI_MAX_SR(55),
		SPEI_RESERVED1(30),
		SPEI_RESERVED2(33),
		SPEI_RESERVED3(63);
		SPEVENTENUM(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPEVENTENUM > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPEVENTENUM > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	/// enum values
	public enum SPEVENTLPARAMTYPE implements IntValuedEnum<SPEVENTLPARAMTYPE > {
		SPET_LPARAM_IS_UNDEFINED(0),
		SPET_LPARAM_IS_TOKEN(((int)SAPI.SPEVENTLPARAMTYPE.SPET_LPARAM_IS_UNDEFINED.value() + 1)),
		SPET_LPARAM_IS_OBJECT(((int)SAPI.SPEVENTLPARAMTYPE.SPET_LPARAM_IS_TOKEN.value() + 1)),
		SPET_LPARAM_IS_POINTER(((int)SAPI.SPEVENTLPARAMTYPE.SPET_LPARAM_IS_OBJECT.value() + 1)),
		SPET_LPARAM_IS_STRING(((int)SAPI.SPEVENTLPARAMTYPE.SPET_LPARAM_IS_POINTER.value() + 1));
		SPEVENTLPARAMTYPE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPEVENTLPARAMTYPE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPEVENTLPARAMTYPE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};

	public enum SPRULESTATE implements IntValuedEnum<SPRULESTATE > {
		SPRS_INACTIVE(0),
		SPRS_ACTIVE(1),
		SPRS_ACTIVE_WITH_AUTO_PAUSE(3),
		SPRS_ACTIVE_USER_DELIMITED(4);
		SPRULESTATE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPRULESTATE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPRULESTATE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};

	public enum SPINTERFERENCE implements IntValuedEnum<SPINTERFERENCE > {
		SPINTERFERENCE_NONE(0),
		SPINTERFERENCE_NOISE(((int)SAPI.SPINTERFERENCE.SPINTERFERENCE_NONE.value() + 1)),
		SPINTERFERENCE_NOSIGNAL(((int)SAPI.SPINTERFERENCE.SPINTERFERENCE_NOISE.value() + 1)),
		SPINTERFERENCE_TOOLOUD(((int)SAPI.SPINTERFERENCE.SPINTERFERENCE_NOSIGNAL.value() + 1)),
		SPINTERFERENCE_TOOQUIET(((int)SAPI.SPINTERFERENCE.SPINTERFERENCE_TOOLOUD.value() + 1)),
		SPINTERFERENCE_TOOFAST(((int)SAPI.SPINTERFERENCE.SPINTERFERENCE_TOOQUIET.value() + 1)),
		SPINTERFERENCE_TOOSLOW(((int)SAPI.SPINTERFERENCE.SPINTERFERENCE_TOOFAST.value() + 1));
		SPINTERFERENCE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPINTERFERENCE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPINTERFERENCE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	/// enum values
	public enum SPENDSRSTREAMFLAGS implements IntValuedEnum<SPENDSRSTREAMFLAGS > {
		SPESF_NONE(0),
		SPESF_STREAM_RELEASED((1 << 0)),
		SPESF_EMULATED((1 << 1));
		SPENDSRSTREAMFLAGS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPENDSRSTREAMFLAGS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPENDSRSTREAMFLAGS > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	/// enum values
	public enum SPVFEATURE implements IntValuedEnum<SPVFEATURE > {
		SPVFEATURE_STRESSED((1 << 0)),
		SPVFEATURE_EMPHASIS((1 << 1));
		SPVFEATURE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPVFEATURE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPVFEATURE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};
	/// enum values
	public enum SPVISEMES implements IntValuedEnum<SPVISEMES > {
		SP_VISEME_0(0),
		SP_VISEME_1(((int)SAPI.SPVISEMES.SP_VISEME_0.value() + 1)),
		SP_VISEME_2(((int)SAPI.SPVISEMES.SP_VISEME_1.value() + 1)),
		SP_VISEME_3(((int)SAPI.SPVISEMES.SP_VISEME_2.value() + 1)),
		SP_VISEME_4(((int)SAPI.SPVISEMES.SP_VISEME_3.value() + 1)),
		SP_VISEME_5(((int)SAPI.SPVISEMES.SP_VISEME_4.value() + 1)),
		SP_VISEME_6(((int)SAPI.SPVISEMES.SP_VISEME_5.value() + 1)),
		SP_VISEME_7(((int)SAPI.SPVISEMES.SP_VISEME_6.value() + 1)),
		SP_VISEME_8(((int)SAPI.SPVISEMES.SP_VISEME_7.value() + 1)),
		SP_VISEME_9(((int)SAPI.SPVISEMES.SP_VISEME_8.value() + 1)),
		SP_VISEME_10(((int)SAPI.SPVISEMES.SP_VISEME_9.value() + 1)),
		SP_VISEME_11(((int)SAPI.SPVISEMES.SP_VISEME_10.value() + 1)),
		SP_VISEME_12(((int)SAPI.SPVISEMES.SP_VISEME_11.value() + 1)),
		SP_VISEME_13(((int)SAPI.SPVISEMES.SP_VISEME_12.value() + 1)),
		SP_VISEME_14(((int)SAPI.SPVISEMES.SP_VISEME_13.value() + 1)),
		SP_VISEME_15(((int)SAPI.SPVISEMES.SP_VISEME_14.value() + 1)),
		SP_VISEME_16(((int)SAPI.SPVISEMES.SP_VISEME_15.value() + 1)),
		SP_VISEME_17(((int)SAPI.SPVISEMES.SP_VISEME_16.value() + 1)),
		SP_VISEME_18(((int)SAPI.SPVISEMES.SP_VISEME_17.value() + 1)),
		SP_VISEME_19(((int)SAPI.SPVISEMES.SP_VISEME_18.value() + 1)),
		SP_VISEME_20(((int)SAPI.SPVISEMES.SP_VISEME_19.value() + 1)),
		SP_VISEME_21(((int)SAPI.SPVISEMES.SP_VISEME_20.value() + 1));
		SPVISEMES(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPVISEMES > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPVISEMES > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};

	public enum SPAUDIOOPTIONS implements IntValuedEnum<SPAUDIOOPTIONS > {
		SPAO_NONE(0),
		SPAO_RETAIN_AUDIO((1 << 0));
		SPAUDIOOPTIONS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPAUDIOOPTIONS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPAUDIOOPTIONS > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};

	public enum SPBOOKMARKOPTIONS implements IntValuedEnum<SPBOOKMARKOPTIONS > {
		SPBO_NONE(0),
		SPBO_PAUSE((1 << 0)),
		SPBO_AHEAD((1 << 1)),
		SPBO_TIME_UNITS((1 << 2));
		SPBOOKMARKOPTIONS(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPBOOKMARKOPTIONS > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPBOOKMARKOPTIONS > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};

	public enum SPCONTEXTSTATE implements IntValuedEnum<SPCONTEXTSTATE > {
		SPCS_DISABLED(0),
		SPCS_ENABLED(1);
		SPCONTEXTSTATE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPCONTEXTSTATE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPCONTEXTSTATE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};

	public enum SPRECOSTATE implements IntValuedEnum<SPRECOSTATE > {
		SPRST_INACTIVE(0),
		SPRST_ACTIVE(((int)SAPI.SPRECOSTATE.SPRST_INACTIVE.value() + 1)),
		SPRST_ACTIVE_ALWAYS(((int)SAPI.SPRECOSTATE.SPRST_ACTIVE.value() + 1)),
		SPRST_INACTIVE_WITH_PURGE(((int)SAPI.SPRECOSTATE.SPRST_ACTIVE_ALWAYS.value() + 1)),
		SPRST_NUM_STATES(((int)SAPI.SPRECOSTATE.SPRST_INACTIVE_WITH_PURGE.value() + 1));
		SPRECOSTATE(long value) {
			this.value = value;
		}
		public final long value;
		public long value() {
			return this.value;
		}
		public Iterator<SPRECOSTATE > iterator() {
			return Collections.singleton(this).iterator();
		}
		public static IntValuedEnum<SPRECOSTATE > fromValue(int value) {
			return FlagSet.fromValue(value, values());
		}
	};

	// Utility methods
	public static final ISpAudio GetAudioObject(String categoryId) throws ClassNotFoundException {
		int hr = COMRuntime.S_OK;
		
		// SpGetDefaultTokenFromCategoryId begin
		
		ISpObjectTokenCategory cpTokenCategory = COMRuntime.newInstance(ISpObjectTokenCategory.class);
		hr = cpTokenCategory.SetId(Pointer.pointerToWideCString(categoryId), 1);
		
		// TODO allocate pointer to be returned
		Pointer<Pointer<?>> pszTokenId = Pointer.allocatePointer();
		hr = cpTokenCategory.GetDefaultTokenId(pszTokenId);
		
		ISpObjectToken pToken = COMRuntime.newInstance(ISpObjectToken.class);
		hr = pToken.SetId(null, pszTokenId.get().as(Character.class), 0);
		
		// SpGetDefaultTokenFromCategoryId end
		
		
		
		// SpCreateObjectFromToken begin
		
		ISpAudio audioObject = null;
		Pointer<Pointer<?>> ppAudio = Pointer.allocatePointer();
		hr = pToken.CreateInstance(null, COMRuntime.CLSCTX_ALL, COMRuntime.getIID(ISpAudio.class), ppAudio);
		if (hr == COMRuntime.S_OK) {
			audioObject = ppAudio.get().getNativeObject(ISpAudio.class);
		}
		
		// SpCreateObjectFromToken end
		
		pToken.Release();
		cpTokenCategory.Release();
		Pointer.release(ppAudio, pszTokenId);
		
		return audioObject;
	}
}
