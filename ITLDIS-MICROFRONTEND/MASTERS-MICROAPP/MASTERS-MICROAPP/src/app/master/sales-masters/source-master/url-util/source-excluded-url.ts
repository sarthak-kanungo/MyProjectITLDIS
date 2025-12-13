import { SourceApi } from './source-urls';

export abstract class SourceExcludedUrl {
    static sourceExcludedUrls: Array<string> = [
        SourceApi.getSourceCodeAutocomplete,
        SourceApi.getSourceNameAutocomplete
    ]
}